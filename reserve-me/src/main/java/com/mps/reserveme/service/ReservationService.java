package com.mps.reserveme.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.mps.reserveme.exception.FirebaseDatabaseException;
import com.mps.reserveme.firebase.Database;
import com.mps.reserveme.firebase.FirebaseAuthentication;
import com.mps.reserveme.model.Reservation;
import com.mps.reserveme.model.Resource;
import com.mps.reserveme.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    FirebaseAuthentication firebaseAuthentication;

    public Reservation getReservationById(String reservationId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(Database.RESERVATIONS.getValue()).document(reservationId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Reservation reservation = document.toObject(Reservation.class);

        if (reservation == null)
            throw new FirebaseDatabaseException(String.format(ServiceMessages.RESERVATION_NOT_FOUND.getValue(), reservationId));

        reservation.setUser(userService.getUserById(reservation.getUserId()));
        return reservation;
    }

    public List<Reservation> getAllReservations() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(Database.RESERVATIONS.getValue()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Reservation> allReservations = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            allReservations.add(getReservationById(document.getId()));
        }

        return allReservations;

    }

    public Reservation createReservation(Reservation reservation, String userId) throws ExecutionException, InterruptedException, ParseException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference newReservation = db.collection(Database.RESERVATIONS.getValue()).document();

        String reservationId = newReservation.getId();
        reservation.setReservationId(reservationId);
        reservation.setUserId(userId);
        reservation.setFinished(false);

        ApiFuture<WriteResult> future = newReservation.set(reservation);
        db.batch().commit();

        WriteResult result = future.get();

        if (result == null)
            throw new FirebaseDatabaseException(ServiceMessages.RESERVATION_NOT_CREATED.getValue());

        log.info(String.format(ServiceMessages.CREATE_RESERVATION_SUCCESS.getValue(), reservationId));

        Resource resource = resourceService.getResourceById(reservation.getResourceId());
        resource.getReservationIds().add(reservation.getReservationId());
        resourceService.updateResource(resource);

        return reservation;
    }

    public Reservation updateReservation(Reservation reservation) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResult = db.collection(Database.RESERVATIONS.getValue()).document(reservation.getReservationId()).set(reservation);

        log.info(String.format(ServiceMessages.UPDATE_RESERVATION_SUCCESS.getValue(), reservation.getReservationId()));

        if (reservation.getFinished()) {
            Resource resource = resourceService.getResourceById(reservation.getResourceId());
            resource.setState("Available");
            resourceService.updateResource(resource);
        }

        return reservation;
    }

    public boolean deleteReservation(String reservationId, String userId) throws ExecutionException, InterruptedException, FirebaseAuthException {
        Reservation reservation = getReservationById(reservationId);
        User user = userService.getUserById(userId);
        if (reservation.getUserId().equals(userId) || user.getRole().equals("admin")) {
            Resource resource = resourceService.getResourceById(reservation.getResourceId());
            List<String> reservationIds = resource.getReservationIds();
            for (String currentReservationId : reservationIds) {
                if (currentReservationId.equals(reservationId)) {
                    resource.getReservationIds().remove(currentReservationId);
                    break;
                }
            }
            List<Reservation> reservations = resource.getReservations();
            for (Reservation currentReservation : reservations) {
                if (currentReservation.getReservationId().equals(reservationId)) {
                    resource.getReservations().remove(currentReservation);
                    break;
                }
            }

            resourceService.updateResource(resource);

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> writeResult = db.collection(Database.RESERVATIONS.getValue()).document(reservationId).delete();
            return true;
        }

        return false;
    }

    public List<Reservation> getReservationsByUserId(String userId) throws ExecutionException, InterruptedException {

        return getAllReservations()
                .stream()
                .filter(reservation -> reservation.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}

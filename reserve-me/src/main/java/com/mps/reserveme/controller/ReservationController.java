package com.mps.reserveme.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.mps.reserveme.dto.UserDto;
import com.mps.reserveme.firebase.FirebaseAuthentication;
import com.mps.reserveme.model.Reservation;
import com.mps.reserveme.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReservationController {
    private static final int SPLIT = 7;

    @Autowired
    private FirebaseAuthentication firebaseAuthentication;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable String reservationId)
            throws ExecutionException, InterruptedException {
        Reservation response = reservationService.getReservationById(reservationId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations()
            throws ExecutionException, InterruptedException {
        List<Reservation> response = reservationService.getAllReservations();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/reservations/users/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable String userId)
            throws ExecutionException, InterruptedException {
        List<Reservation> response = reservationService.getReservationsByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * @param reservation
     * @param token
     * @return
     * @throws FirebaseAuthException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/addReservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation,
                                                         @RequestHeader(name = "Authorization") String token)
            throws FirebaseAuthException, ExecutionException, InterruptedException, ParseException {

        String userId = firebaseAuthentication.getUid(token.substring(SPLIT));

        Reservation response = reservationService.createReservation(reservation, userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/updateReservation")
    public ResponseEntity<Object> updateReservation(@RequestBody Reservation reservation,
                                                    @RequestHeader(name = "Authorization") String token) throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid(token.substring(SPLIT));
        if (userId.equals(reservation.getUserId())) {
            Reservation response = reservationService.updateReservation(reservation);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            UserDto userDto = new UserDto("Cannot update another person's reservation!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userDto);
        }
    }

    @DeleteMapping("/deleteReservation/{reservationId}")
    public ResponseEntity deleteReservation(@PathVariable String reservationId,
                                            @RequestHeader(name = "Authorization") String token)
            throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid(token.substring(SPLIT));

        boolean success = reservationService.deleteReservation(reservationId, userId);

        if (success) {
            UserDto userDto = new UserDto("Successfully deleted reservation");
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        }

        UserDto userDto = new UserDto("Cannot delete a reservation that is not yours!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userDto);
    }


}

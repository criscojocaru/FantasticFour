package com.mps.reserveme.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.mps.reserveme.exception.FirebaseDatabaseException;
import com.mps.reserveme.firebase.Database;
import com.mps.reserveme.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

    /**
     * @param user
     * @return
     */
    public User updateUser(User user) {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResult = db.collection(Database.USERS.getValue()).document(user.getUserId()).set(user);

        log.info(String.format(ServiceMessages.UPDATE_USER_SUCCESS.getValue(), user.getUserId()));

        return user;
    }

    /**
     * @param userId
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public User getUserById(String userId) throws InterruptedException, ExecutionException {

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(Database.USERS.getValue()).document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        User user = document.toObject(User.class);

        if (user == null)
            throw new FirebaseDatabaseException(String.format(ServiceMessages.USER_NOT_FOUND.getValue(), userId));

        log.info(String.format(ServiceMessages.GET_USER_SUCCESS.getValue(), userId));

        return user;
    }

    /**
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<User> getAllUsers() throws InterruptedException, ExecutionException {

        List<User> allUsers = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(Database.USERS.getValue()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            allUsers.add(getUserById(document.getId()));
        }

        return allUsers;
    }

    /**
     * @param user
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws FirebaseAuthException
     */
    public String loginUser(User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        List<User> users = getAllUsers();
        User foundUser = users.stream()
                        .filter(currentUser -> currentUser.getEmail().equals(user.getEmail())
                            && currentUser.getPassword().equals(DigestUtils.sha256Hex(user.getPassword())))
                        .findFirst()
                        .orElse(null);

        if (foundUser != null)
            return FirebaseAuth.getInstance().createCustomToken(foundUser.getUserId());

        return null;
    }
}



package com.mps.reserveme.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.mps.reserveme.exception.FirebaseDatabaseException;
import com.mps.reserveme.firebase.Database;
import com.mps.reserveme.model.Token;
import com.mps.reserveme.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

    @Autowired
    ModelMapper modelMapper;

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
    public String loginUser(User user) throws ExecutionException, InterruptedException, FirebaseAuthException, IOException {
        List<User> users = getAllUsers();
        User foundUser = users.stream()
                        .filter(currentUser -> currentUser.getEmail().equals(user.getEmail())
                            && currentUser.getPassword().equals(DigestUtils.sha256Hex(user.getPassword())))
                        .findFirst()
                        .orElse(null);

        if (foundUser != null) {
            String customToken = FirebaseAuth.getInstance().createCustomToken(foundUser.getUserId());
            String tokenId = exchangeTokens(customToken);
            ObjectMapper objectMapper  = new ObjectMapper();
            Token token = objectMapper.readValue(tokenId, Token.class);
            return token.getIdToken();
        }

        return null;
    }

    private String exchangeTokens(String customToken) throws IOException {

        String webAPIKey = "AIzaSyATBdVS8h8KnxIm_HZHN9dO9ONixg4QVqM";
        String request = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key=" + webAPIKey;

        URL url = new URL(request);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\"token\": \"" + customToken + "\", \"returnSecureToken\": true}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response.toString());
            return response.toString();
        }

    }
}



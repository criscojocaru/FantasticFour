package com.mps.reserveme.firebase;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.mps.reserveme.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class FirebaseAuthentication {

    public UserRecord registerUser(User user) throws FirebaseAuthException {
        Firestore db = FirestoreClient.getFirestore();

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword())
                .setDisplayName(user.getFirstName() + " " + user.getLastName())
                .setDisabled(false);


        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        user.setUserId(userRecord.getUid());
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        user.setSubscribed(new ArrayList<>());

        // create normal users using custom claims
        Map<String, Object> claims = new HashMap<>();
        if (user.getRole().equals("admin")) {
            claims.put("admin", true);
        } else {
            claims.put("admin", false);
        }
        FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

        ApiFuture<WriteResult> future = db.collection(Database.USERS.getValue()).document(user.getUserId()).set(user);

        return  userRecord;
    }

    public Boolean checkAdminRole(String token) throws FirebaseAuthException {
        FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(token);

        return Boolean.TRUE.equals(decoded.getClaims().get("admin"));
    }


    public String getUid(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

        return decodedToken.getUid();
    }
}

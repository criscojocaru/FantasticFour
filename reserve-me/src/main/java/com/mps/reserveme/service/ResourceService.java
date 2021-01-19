package com.mps.reserveme.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mps.reserveme.exception.FirebaseDatabaseException;
import com.mps.reserveme.firebase.Database;
import com.mps.reserveme.model.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class ResourceService {

    public Resource getResourceById(String resourceId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(Database.RESOURCES.getValue()).document(resourceId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Resource resource = document.toObject(Resource.class);

        if(resource == null)
            throw new FirebaseDatabaseException(String.format(ServiceMessages.RESOURCE_NOT_FOUND.getValue(), resourceId));

        return resource;
    }

    public List<Resource> getAllResources() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(Database.RESOURCES.getValue()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Resource> allResources = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            allResources.add(getResourceById(document.getId()));
        }

        return allResources;

    }

    public Resource createResource(Resource resource) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference newResource = db.collection(Database.RESOURCES.getValue()).document();

        String resourceId = newResource.getId();
        resource.setResourceId(resourceId);
        resource.setState("Free");
        ApiFuture<WriteResult> future = newResource.set(resource);
        db.batch().commit();

        WriteResult result = future.get();

        if(result == null)
            throw new FirebaseDatabaseException(ServiceMessages.RESOURCE_NOT_CREATED.getValue());

        log.info(String.format(ServiceMessages.CREATE_RESOURCE_SUCCESS.getValue(), resourceId));

        return resource;
    }

    public Resource updateResource(Resource resource) {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResult = db.collection(Database.RESOURCES.getValue()).document(resource.getResourceId()).set(resource);

        log.info(String.format(ServiceMessages.UPDATE_RESOURCE_SUCCESS.getValue(), resource.getResourceId()));

        return resource;
    }
}

package com.mps.reserveme.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.mps.reserveme.firebase.FirebaseAuthentication;
import com.mps.reserveme.model.Resource;
import com.mps.reserveme.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResourceController {
    private static final int SPLIT = 7;

    @Autowired
    ResourceService resourceService;

    @Autowired
    private FirebaseAuthentication firebaseAuthentication;

    @GetMapping("/resources/{resourceId}")
    public ResponseEntity<Resource> getResourcesById(@PathVariable String resourceId)
            throws ExecutionException, InterruptedException {
        Resource response = resourceService.getResourceById(resourceId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/resources")
    public ResponseEntity<List<Resource>> getAllResources()
            throws ExecutionException, InterruptedException {
        List<Resource> response = resourceService.getAllResources();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * @param resource
     * @param token
     * @return
     * @throws FirebaseAuthException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/addResource")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource,
                                                   @RequestHeader(name = "Authorization") String token)
            throws FirebaseAuthException, ExecutionException, InterruptedException {

        if (!firebaseAuthentication.checkAdminRole(token.substring(SPLIT)))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Resource response = resourceService.createResource(resource);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/updateResource")
    public ResponseEntity<Resource> updateResource(@RequestBody Resource resource) {
        Resource response = resourceService.updateResource(resource);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

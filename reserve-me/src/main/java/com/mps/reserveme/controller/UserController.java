package com.mps.reserveme.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.mps.reserveme.dto.AuthDto;
import com.mps.reserveme.dto.UserDto;
import com.mps.reserveme.firebase.FirebaseAuthentication;
import com.mps.reserveme.model.Resource;
import com.mps.reserveme.model.User;
import com.mps.reserveme.service.ResourceService;
import com.mps.reserveme.service.ServiceMessages;
import com.mps.reserveme.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    private static final int SPLIT = 7;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private FirebaseAuthentication firebaseAuthentication;


    @Autowired
    private ModelMapper modelMapper;


    /**
     * @param user
     * @return
     * @throws FirebaseAuthException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/registerUser")
    public ResponseEntity<AuthDto> registerUser(@RequestBody User user) throws FirebaseAuthException, ExecutionException, InterruptedException {

        User userExists = userService.getAllUsers().stream().filter(user1 -> user1.getEmail().equals(user.getEmail())).findFirst().orElse(null);

        if (userExists != null) {
            AuthDto response = new AuthDto(userExists.getUserId(), new Date().toString(), "Email already used!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        UserRecord userRecord = firebaseAuthentication.registerUser(user);
        AuthDto response = modelMapper.map(userRecord, AuthDto.class);

        String message = response.registerUserDtoMessage(response.getUid(), response.getTimeStamp());
        response.setMessage(message);

        log.info(message);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/loginUser")
    public ResponseEntity loginUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException, IOException {

        String token = userService.loginUser(user);
        if (token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password!");
    }


    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
            throws ExecutionException, InterruptedException {

        List<User> response = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * @param userId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId)
            throws ExecutionException, InterruptedException {

        User response = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/users/userProfile")
    public ResponseEntity<User> getUserProfile(@RequestHeader(name = "Authorization") String token)
            throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid(token.substring(SPLIT));
        User response = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * @param user
     * @param token
     * @return
     * @throws FirebaseAuthException
     */
    @PutMapping("/users/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user,
                                              @RequestHeader(name = "Authorization") String token)
            throws FirebaseAuthException {

        String userId = firebaseAuthentication.getUid(token.substring(SPLIT));
        user.setUserId(userId);

        User response = userService.updateUser(user);

        String message = String.format(ServiceMessages.UPDATE_USER_SUCCESS.getValue(), response.getUserId());

        UserDto userDto = new UserDto(message);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @PutMapping("/subscribe/{resourceId}")
    public ResponseEntity<UserDto> subscribeToResource(@PathVariable String resourceId,
                                                       @RequestHeader(name = "Authorization") String token)
            throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid((token.substring(SPLIT)));
        User response = userService.subscribeToResource(userId, resourceId);

        String message = String.format(ServiceMessages.UPDATE_USER_SUCCESS.getValue(), response.getUserId());

        UserDto userDto = new UserDto(message);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/unsubscribe/{resourceId}")
    public ResponseEntity<UserDto> unsubscribeToResource(@PathVariable String resourceId,
                                                         @RequestHeader(name = "Authorization") String token)
            throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid((token.substring(SPLIT)));
        User response = userService.unsubscribeToResource(userId, resourceId);

        String message = String.format(ServiceMessages.UPDATE_USER_SUCCESS.getValue(), response.getUserId());

        UserDto userDto = new UserDto(message);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @GetMapping("/users/subscribed")
    public ResponseEntity<List<Resource>> getSubscribedResourcesForCurrentUser(@RequestHeader(name = "Authorization") String token)
            throws ExecutionException, InterruptedException, FirebaseAuthException {

        String userId = firebaseAuthentication.getUid((token.substring(SPLIT)));
        User response = userService.getUserById(userId);

        List<String> subscribedResourceIds = response.getSubscribed();
        List<Resource> subscribedResources = new ArrayList<>();
        for (String subscribedResourceId : subscribedResourceIds) {
            subscribedResources.add(resourceService.getResourceById(subscribedResourceId));
        }

        return ResponseEntity.status(HttpStatus.OK).body(subscribedResources);
    }
}

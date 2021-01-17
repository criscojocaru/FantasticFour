package com.mps.reserveme.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.mps.reserveme.dto.AuthDto;
import com.mps.reserveme.dto.UserDto;
import com.mps.reserveme.firebase.FirebaseAuthentication;
import com.mps.reserveme.model.User;
import com.mps.reserveme.service.ServiceMessages;
import com.mps.reserveme.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<AuthDto> registerUser(@RequestBody User user) throws FirebaseAuthException {

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


    /**
     * @param user
     * @param token
     * @return
     * @throws FirebaseAuthException
     */
    @PutMapping("/users/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user,
                                              @RequestHeader(name="Authorization") String token)
            throws FirebaseAuthException {

        if (!firebaseAuthentication.checkAdminRole(token.substring(SPLIT)))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        User response = userService.updateUser(user);

        String message = String.format(ServiceMessages.UPDATE_USER_SUCCESS.getValue(), response.getUserId());

        UserDto userDto = new UserDto(message);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


}

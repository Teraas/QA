package com.famly.controllers;

import com.famly.entity.User;
import com.famly.entity.UserRelation;
import com.famly.repository.UserRelationRepository;
import com.famly.repository.UserRepository;
import com.famly.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/UserService")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRelationRepository userRelationRepository;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World, from users service";
    }

    @GetMapping(path = "/getAllUsers", produces = "application/json")
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users;
    }
    @GetMapping(path = "getUser/{id}", produces = "application/json")
    public User getUserDetails(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        User user = null;
        try{
            user = userRepository.findById(id).get();
            System.out.println("[Monitoring] - [Debug] " + user);
        } catch(Exception e){
            System.out.println("[Monitoring] - [exception]" +e.getMessage());
            return null;
        }


        return user;
    }

    @PostMapping(path = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User _user = userRepository
                    .save(user);
            return new ResponseEntity<>(_user.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(path = "/getUserRelations/{id}", produces = "application/json")
    public List<?> getUserRelations(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        System.out.println(id.getClass().toString());
        List<?> userRelations = userRelationRepository.findByUserXid(id);
        System.out.println(userRelations);
        return userRelations;
    }


    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public String registerUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        /** Using Google firestore.
         * TODO - move to AWS RDS
         */
        return userService.createUser(user);
    }



}

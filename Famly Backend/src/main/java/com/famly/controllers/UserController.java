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
            System.out.println(user);
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

    @GetMapping(path = "/getUserTree/{id}", produces = "application/json")
    public Map<Long, List<?>> getUserTree(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        int maxRelations = 500;
        Queue<Long> queue = new LinkedList<>();
        List<UserRelation> userRelations = userRelationRepository.findByUserXid(id);
        System.out.println(userRelations);
        /** get all relations recursively and store in a Graph.
         *  Hit1 - get first neighbours, store them in a queque.
         *  Store all neighbours of the listQueue
         */
        Map<Long, List<?>> relationGraphAdjList = new HashMap<>();
        relationGraphAdjList.put(id, userRelations);
        userRelations.forEach( relation -> queue.add(relation.getUserYid()));
        maxRelations = maxRelations - userRelations.size();
        while(!queue.isEmpty() && maxRelations>0){
            Long relatedID = queue.remove();
            maxRelations --;
            userRelations = userRelationRepository.findByUserXid(relatedID);
            relationGraphAdjList.put(relatedID,userRelations);
            if(userRelations !=null && !userRelations.isEmpty()){
                relationGraphAdjList.put(relatedID,userRelations);
                userRelations.forEach( relation -> queue.add(relation.getUserYid()));
            }
        }
        return relationGraphAdjList;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public String registerUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        /** Using Google firestore.
         * TODO - move to AWS RDS
         */
        return userService.createUser(user);
    }



}

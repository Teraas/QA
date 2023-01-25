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
@RequestMapping(path = "/FamlyService")
public class FamlyController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRelationRepository userRelationRepository;

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

}

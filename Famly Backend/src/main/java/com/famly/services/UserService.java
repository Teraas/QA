package com.famly.services;

import com.famly.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Service using NoSQL db

/**
 * TODO - create and interface for it and implement the service
 * To avoid implementation issues in future
 */
@Service
public class UserService {
    public static final String COL_NAME="users";

    public List getAllUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> userList = new ArrayList();

        return userList;
    }

    public void getUserDetails(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public void updateUser(User user){

    }


}

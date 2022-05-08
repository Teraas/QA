package com.famly.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class dbFirestore {
    private static String DB = "https://familytree-bee8f-default-rtdb.asia-southeast1.firebasedatabase.app/";

    public dbFirestore() throws IOException {
    }

    @PostConstruct
    public void initialize() throws IOException {

        try{
            String homePath = Paths.get(".").toAbsolutePath().normalize().toString();
            FileInputStream serviceAccount = new FileInputStream(homePath + "/src/main/resources/firebaseKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DB).build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }


}
}

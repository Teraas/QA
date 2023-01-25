package com.example.familytree.Data.ReaderAPIs.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.Log;

import com.example.familytree.Data.WriterAPIs.UserDataWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class Login {
    private String username;
    private String password;

    public String getToken(String email, String password){
        this.password = password;
        this.username = email;
        String token = "";
        try {

            URL githubEndpoint = new URL("http://localhost:8088/authenticate");
            String formData = "{\n    \"username\":\"harish@gmail.com\",\n    \"password\":\"password1\"\n}";
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) githubEndpoint.openConnection();
            Log.d("[ Login ]","here 0");
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestMethod("POST");
            //myConnection.setDoOutput(true);
            //myConnection.setRequestProperty("charset", "utf-8");
            //myConnection.setRequestProperty("Content-Length", Integer.toString(formData.length()));
            Log.d("[ Login ]","here 1");
            //myConnection.getURL();
            OutputStream out = myConnection.getOutputStream();
            out.write(formData.getBytes(StandardCharsets.UTF_8));
            InputStream in = myConnection.getInputStream();
            Log.d("[ Login ]","here 2");
            //InputStreamReader responseBodyReader = new InputStreamReader(in, "UTF-8");
            Log.d("[ Login ]", "responseBodyReader.toString()");
            AuthToken authToken = new ObjectMapper().readValue(in, AuthToken.class);
            //JsonReader jsonReader = new JsonReader(responseBodyReader);
            Log.d("[ Login ]", "authToken.toString()");
            //String key = jsonReader.nextName();
            token = authToken.getJwt();
            // responseBody.prettyPrint());
        } catch (Exception e) {
            Log.d("[ Login ]", "got exception " + e.getMessage());
        }
        return token;
    }

    public String getToken2(String email, String password) throws IOException, JSONException {
        this.password = password;
        this.username = email;
        String token = "";

        UserDataWriter writer = new UserDataWriter();
        token = writer.userSignin(email, password);
        Log.d("[ Login ]", token);
        return token;
    }
}

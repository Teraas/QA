package com.example.familytree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.familytree.Data.WriterAPIs.UserDataWriter;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String mypreference = "famlyPref";
    UserDataWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        writer = new UserDataWriter(sharedpreferences);
        String token="abc";
        if(sharedpreferences.contains("token")) {
            token = sharedpreferences.getString("token", "");
        }

        Log.d("Harish Token ", "token");
        Log.d("Harish Token1 ", token);

    }
}
package com.example.familytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.familytree.Data.ReaderAPIs.auth.Login;
import com.example.familytree.Data.WriterAPIs.UserDataWriter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail;
    private EditText textPassword;
    private Button regLoginButton;

    private UserDataWriter writer;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "famlyPref";

    Toolbar mToolbar;

    //TODO - progress bar
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = (EditText) findViewById(R.id.reg_loginEmailAddress);
        textPassword = (EditText) findViewById(R.id.reg_loginPassword);
        regLoginButton = (Button) findViewById(R.id.reg_loginButton);

        progressDialog = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        writer = new UserDataWriter();
        writer.sharedpreferences = sharedpreferences;
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // create auth Object
        login = new Login();

        regLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                Log.d("[ Auth Debug ]", "email is - " + email + " and password is - " + password);
                if( !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) ) {
                    progressDialog.setTitle("Logging In");
                    progressDialog.setMessage("Authentication is in Progress!");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    String token = "";

                    try {
                        //loginUser(email, password);
                        token = login.getToken2(email, password);
                        Log.d("[ Auth Debug ]", token);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        Log.d("[ Auth Debug] ", "Updating token in pref " + token);
                        editor.putString("token", token);
                        editor.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(token == null) {
                        progressDialog.hide();
                        Toast.makeText(LoginActivity.this, "Authentication failed, please check the form and try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();

                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() ) {
                    progressDialog.dismiss();

                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    try {
                        String token;
                        Log.d("[ Auth Debug ]", "Token Before Auth");
                        token = writer.userSignin(email, password);
//                        Toast.makeText(LoginActivity.this, "Token Authenticated...... "+ token,
//                                Toast.LENGTH_SHORT).show();
                        Log.d("[ Auth Debug ] token- ", token);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    progressDialog.hide();

                    Toast.makeText(LoginActivity.this, "Authentication failed, please check the form and try again.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUserWithAPI(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() ) {
                    progressDialog.dismiss();

                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    try {
                        String token;
                        Log.d("[ Auth Debug ]", "Token Before Auth");
                        token = writer.userSignin(email, password);
//                        Toast.makeText(LoginActivity.this, "Token Authenticated...... "+ token,
//                                Toast.LENGTH_SHORT).show();
                        Log.d("[ Auth Debug ] token- ", token);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    progressDialog.hide();

                    Toast.makeText(LoginActivity.this, "Authentication failed, please check the form and try again.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
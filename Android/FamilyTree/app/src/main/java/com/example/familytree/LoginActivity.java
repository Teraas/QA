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
        writer = new UserDataWriter(sharedpreferences);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        regLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

                if( !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) ) {
                    progressDialog.setTitle("Logging In");
                    progressDialog.setMessage("Authentication is in Progress!");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    loginUser(email, password);
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
                        Log.d("Token Before Auth", "empty");
                        token = writer.userSignin(email, password);
//                        Toast.makeText(LoginActivity.this, "Token Authenticated...... "+ token,
//                                Toast.LENGTH_SHORT).show();
                        Log.d("Token Authenticated.. ", token);
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
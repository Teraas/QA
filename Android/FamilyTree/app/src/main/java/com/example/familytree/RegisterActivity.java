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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private EditText textDisplayName;
    private EditText textEmail;
    private EditText textPassword;
    private Button regButton;

    private UserDataWriter writer;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "famlyPref";
    Toolbar mToolbar;

    //TODO - progress bar
    private ProgressDialog progressDiaglog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        textDisplayName = (EditText) findViewById(R.id.reg_PersonName);
        textEmail = (EditText) findViewById(R.id.reg_EmailAddress);
        textPassword = (EditText) findViewById(R.id.reg_TextPassword2);
        regButton = (Button) findViewById(R.id.reg_Button);
        writer = new UserDataWriter();
        writer.sharedpreferences = sharedpreferences;
        progressDiaglog = new ProgressDialog(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_Register);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = textDisplayName.getText().toString();
                String mUserEmail = textEmail.getText().toString();
                String mUserPassword = textPassword.getText().toString();
                if( !TextUtils.isEmpty(mUserName) || !TextUtils.isEmpty(mUserEmail) || !TextUtils.isEmpty(mUserPassword) ) {
                    progressDiaglog.setTitle("User Registration");
                    progressDiaglog.setMessage("Account is getting created!");
                    progressDiaglog.setCanceledOnTouchOutside(false);
                    progressDiaglog.show();
                    try {
                        registerUser(mUserEmail, mUserPassword);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void registerUser(String mUserEmail, String mUserPassword) throws JSONException, IOException {
        String token = writer.userSignin(mUserEmail, mUserPassword);
        mAuth.createUserWithEmailAndPassword(mUserEmail, mUserPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDiaglog.dismiss();
                            // TODO - update user data in DB, implement API or use firebase

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Harish ", "signInWithCustomToken:success");
                            Log.d("Harish Token Register", token);
                            try {
                                writer.userSignin(mUserEmail, mUserPassword);
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent mainPageIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            mainPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainPageIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Harish ", mUserEmail);
                            Log.d("Harish ", mUserPassword);
                            progressDiaglog.hide();
                            Toast.makeText(RegisterActivity.this, "Authentication failed, please check the form and try again.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HarishError ", e.toString());
            }
            });

    }
}
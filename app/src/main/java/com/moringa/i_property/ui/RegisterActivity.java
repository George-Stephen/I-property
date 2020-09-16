package com.moringa.i_property.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringa.i_property.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.LoginText) TextView mLoginText;
    @BindView(R.id.RegisterName) EditText mUsername;
    @BindView(R.id.RegisterPhone) EditText mPhone;
    @BindView(R.id.RegisterEmail) EditText mEmail;
    @BindView(R.id.RegisterPassword) EditText mPassword;
    @BindView(R.id.RegisterButton) Button mRegisterButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
    private String Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthListener();
        createProgressDialog();



        mLoginText.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLoginText){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mRegisterButton){
            createUser();
        }
    }

    private void createUser(){
        Username = mUsername.getText().toString();
        String phone = mPhone.getText().toString();
         String email = mEmail.getText().toString();
         String password = mPassword.getText().toString();
         boolean validUsername = isValidName(Username);
         boolean validEmail = isValidEmail(email);
         boolean validPassword = isValidPassword(password);
        if (!validEmail || !validUsername || ! validPassword) return;
        progressDialog.dismiss();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Authentication success",Toast.LENGTH_LONG).show();
                    createFirebaseProfile(task.getResult().getUser());
                }else{
                    Toast.makeText(RegisterActivity.this,"Authentication failed",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void createFirebaseProfile(final FirebaseUser user){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(Username)
                            .build();
        user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Log.d("Activity", "" + user.getDisplayName());

                }

            }
        });
    }

    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            mEmail.setError("Please enter a valid email Address");
            return false;
        }
        return true;
    }
    private boolean isValidName(String name){
        if (name.equals("")){
            mUsername.setError("please  enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String Password){
        if (Password.length() < 6) {
            mPassword.setError("The password should be at least 6 Characters");
            return false;
        }
        return true;
    }
    private void createAuthListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Creating your account");
        progressDialog.setCancelable(false);
    }
}
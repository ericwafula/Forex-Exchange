package com.moringaschool.forexexchange.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.forexexchange.R;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(com.moringaschool.forexexchange.R.id.name) EditText mName;
    @BindView(com.moringaschool.forexexchange.R.id.email) EditText mEmail;
    @BindView(com.moringaschool.forexexchange.R.id.password) EditText mPassword;
    @BindView(R.id.confirmPassword) EditText mConfirmPassword;
    @BindView(com.moringaschool.forexexchange.R.id.signupButton) Button signupButton;
    @BindView(com.moringaschool.forexexchange.R.id.alreadyHaveAnAccount) TextView alreadyHaveAnAccount;

    public static final String TAG = CreateAccount.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        ButterKnife.bind(this);

        signupButton.setOnClickListener(this);
        alreadyHaveAnAccount.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        createAuthStateListener();
    }

    @Override
    public void onClick(View view) {
        if (view == signupButton){
            createNewUser();
        } else if (view == alreadyHaveAnAccount){
            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void createNewUser(){
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        Log.d(TAG, "Authentication successful");
                    } else {
                        Toast.makeText(CreateAccount.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Intent intent = new Intent(CreateAccount.this, Converter.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
//
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
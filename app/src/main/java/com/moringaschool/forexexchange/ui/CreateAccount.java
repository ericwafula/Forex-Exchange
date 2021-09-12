package com.moringaschool.forexexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.forexexchange.R;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    @BindView(com.moringaschool.forexexchange.R.id.firstName) EditText mFirstName;
    @BindView(com.moringaschool.forexexchange.R.id.lastName) EditText mLastName;
    @BindView(com.moringaschool.forexexchange.R.id.userName) EditText mUsername;
    @BindView(com.moringaschool.forexexchange.R.id.email) EditText mEmail;
    @BindView(com.moringaschool.forexexchange.R.id.password) EditText mPassword;
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
    }

    @Override
    public void onClick(View view) {
        if (view == signupButton){
            String firstName = mFirstName.getText().toString();
            String lastName = mLastName.getText().toString();
            String username = mUsername.getText().toString();
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            if(!(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty())){
                Intent intent = new Intent(CreateAccount.this, Converter.class);
                intent.putExtra("firstName", firstName);
                startActivity(intent);
            } else {
                Toast.makeText(CreateAccount.this, "All fields must be filled", Toast.LENGTH_LONG).show();
            }

        } else if (view == alreadyHaveAnAccount){
            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
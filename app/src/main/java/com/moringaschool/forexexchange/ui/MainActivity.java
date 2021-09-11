package com.moringaschool.forexexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.forexexchange.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(com.moringaschool.forexexchange.R.id.loginButton) Button mLogin;
    @BindView(com.moringaschool.forexexchange.R.id.username) EditText mUsername;
    @BindView(com.moringaschool.forexexchange.R.id.password) EditText mPassword;
    @BindView(com.moringaschool.forexexchange.R.id.createAnAccountText) TextView createAnAccount;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mLogin.setOnClickListener(this);
        createAnAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mLogin){
            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();

            if(!(username.isEmpty() || password.isEmpty())){
                Intent intent = new Intent(MainActivity.this, Converter.class);
                intent.putExtra("username", username);
                startActivity(intent);
            } else{
                Toast.makeText(MainActivity.this, "username and password fields are not supposed to be empty", Toast.LENGTH_LONG).show();
            }


        } else if (view == createAnAccount){
            Intent intent = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(intent);
        }
    }
}
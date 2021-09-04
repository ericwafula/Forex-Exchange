package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Converter extends AppCompatActivity {
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        user = (TextView) findViewById(R.id.firstName);
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String userName = intent.getStringExtra("username");

        if (firstName.length() != 0){
            user.setText("Welcome " + firstName);
        } else {
            user.setText("Welcome " + userName);
        }
    }
}
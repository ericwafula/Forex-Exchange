package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class converter extends AppCompatActivity {
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        user = (TextView) findViewById(R.id.user);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        user.setText("Welcome " + username);
    }
}
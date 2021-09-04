package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    EditText firstName;
    EditText password;
    Button signupButton;
    public static final String TAG = CreateAccount.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        password = (EditText) findViewById(R.id.password);
        signupButton = (Button) findViewById(R.id.signupButton);
        firstName = (EditText) findViewById(R.id.firstName);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = firstName.getText().toString();
                Toast.makeText(CreateAccount.this, "Account successfully created", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateAccount.this, Converter.class);
                intent.putExtra("firstname", firstname);
                startActivity(intent);
            }
        });
    }
}
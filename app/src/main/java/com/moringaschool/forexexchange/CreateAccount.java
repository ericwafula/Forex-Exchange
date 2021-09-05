package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.firstName) EditText mFirstName;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.signupButton) Button signupButton;
    @BindView(R.id.alreadyHaveAnAccount) TextView alreadyHaveAnAccount;

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
            Toast.makeText(CreateAccount.this, firstName, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CreateAccount.this, Converter.class);
            intent.putExtra("firstName", firstName);
            startActivity(intent);
        } else if (view == alreadyHaveAnAccount){
            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
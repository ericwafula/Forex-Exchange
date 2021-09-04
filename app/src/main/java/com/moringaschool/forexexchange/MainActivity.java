package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mLogin;
    EditText mUsername;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogin = (Button) findViewById(R.id.loginButton);
        mUsername = (EditText) findViewById(R.id.username);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                Log.d(TAG, username);
                Toast.makeText(MainActivity.this, username, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, converter.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
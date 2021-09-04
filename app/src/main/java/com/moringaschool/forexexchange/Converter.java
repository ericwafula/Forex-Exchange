package com.moringaschool.forexexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Converter extends AppCompatActivity {
    TextView mUser;
    String[] currencies = new String[] {
            "US Dollar(USD)", "Canadian DollarCAD", "British Pound(GBP)", "Euro(EUR)",
            "Australian Dollar(AUD)", "Swiss Franc(CHF)", "New Zealand Dollar(NZD)",
            "Japanese Yen(JPY)"
    };

    ListView mCurrencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        mUser = (TextView) findViewById(R.id.firstName);
        mCurrencyList = (ListView) findViewById(R.id.currencies);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String userName = intent.getStringExtra("username");

        if (firstName.length() != 0){
            mUser.setText("Welcome " + firstName);
        } else {
            mUser.setText("Welcome " + userName);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currencies);
        mCurrencyList.setAdapter(adapter);
    }
}
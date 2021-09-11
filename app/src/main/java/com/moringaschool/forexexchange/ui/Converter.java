package com.moringaschool.forexexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.forexexchange.R;

public class Converter extends AppCompatActivity {
    @BindView(com.moringaschool.forexexchange.R.id.user) TextView mUser;
    @BindView(com.moringaschool.forexexchange.R.id.currencies) ListView mCurrencyList;

    String[] currencies = new String[] {
            "US Dollar(USD)", "Canadian DollarCAD", "British Pound(GBP)", "Euro(EUR)",
            "Australian Dollar(AUD)", "Swiss Franc(CHF)", "New Zealand Dollar(NZD)",
            "Japanese Yen(JPY)"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String userName = intent.getStringExtra("username");

        mUser.setText("Welcome " + firstName);

        mUser.setText("Welcome " + userName);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currencies);
        mCurrencyList.setAdapter(adapter);

        mCurrencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currency = ((TextView) view).getText().toString();
                Toast.makeText(Converter.this, currency, Toast.LENGTH_LONG).show();
            }
        });
    }
}
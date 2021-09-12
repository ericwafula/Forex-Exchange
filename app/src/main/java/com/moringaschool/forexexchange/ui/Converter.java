package com.moringaschool.forexexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.forexexchange.ConversionRates;
import com.moringaschool.forexexchange.R;
import com.moringaschool.forexexchange.models.USDRateResponse;
import com.moringaschool.forexexchange.network.Constants;
import com.moringaschool.forexexchange.network.ForexExchangeApi;
import com.moringaschool.forexexchange.network.ForexExchangeClient;

import java.util.ArrayList;
import java.util.List;

public class Converter extends AppCompatActivity implements View.OnClickListener{
    @BindView(com.moringaschool.forexexchange.R.id.user) TextView mUser;
    @BindView(com.moringaschool.forexexchange.R.id.currencies) ListView mCurrencyList;
    @BindView(R.id.baseCurrency) EditText mBaseCurrency;
    @BindView(R.id.quoteCurrency) EditText mQuoteCurrency;
    @BindView(R.id.calculateButton) Button mCalculate;
    @BindView(R.id.subheading) TextView mSubheading;

    List<String> individualCurrency = new ArrayList<>();
    private String baseCurrency;
    private String quoteCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        ButterKnife.bind(this);
        mCalculate.setOnClickListener(this);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String userName = intent.getStringExtra("username");

        mUser.setText("Welcome " + firstName);

        mUser.setText("Welcome " + userName);

        mCurrencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currency = ((TextView) view).getText().toString();
                Toast.makeText(Converter.this, currency, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view == mCalculate){
            baseCurrency = mBaseCurrency.getText().toString().toUpperCase();
            quoteCurrency = mQuoteCurrency.getText().toString().toUpperCase();
            mSubheading.setText(R.string.subheading);

            Toast.makeText(Converter.this, baseCurrency + ", " + quoteCurrency, Toast.LENGTH_LONG).show();

            ForexExchangeApi client = ForexExchangeClient.getClient();
            Call<USDRateResponse> call = client.getCurrencies(Constants.EXCHANGE_RATE_API_KEY, "USD");

            call.enqueue(new Callback<USDRateResponse>() {
                @Override
                public void onResponse(Call<USDRateResponse> call, Response<USDRateResponse> response) {
                    ConversionRates currenciesObject = response.body().getConversionRates();

                    individualCurrency.add("USD " + currenciesObject.getUsd().toString());
                    individualCurrency.add("AUD " + currenciesObject.getAud().toString());
                    individualCurrency.add("GBP " + currenciesObject.getGbp().toString());
                    individualCurrency.add("CAD " + currenciesObject.getCad().toString());

                    ArrayAdapter adapter = new ArrayAdapter(Converter.this, android.R.layout.simple_list_item_1, individualCurrency);
                    mCurrencyList.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<USDRateResponse> call, Throwable t) {

                }
            });
        }
    }
}
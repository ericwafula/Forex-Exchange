package com.moringaschool.forexexchange.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.forexexchange.ConversionRates;
import com.moringaschool.forexexchange.R;
import com.moringaschool.forexexchange.models.USDRateResponse;
import com.moringaschool.forexexchange.network.Constants;
import com.moringaschool.forexexchange.network.ForexExchangeApi;
import com.moringaschool.forexexchange.network.ForexExchangeClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Converter extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mCurrencyPair;
    public static final String TAG = Converter.class.getSimpleName().toString();
    private ValueEventListener mCurrencyPairListener;

    @BindView(com.moringaschool.forexexchange.R.id.user) TextView mUser;
    @BindView(com.moringaschool.forexexchange.R.id.currencies) ListView mCurrencyList;
    @BindView(R.id.baseCurrency) EditText mBaseCurrency;
    @BindView(R.id.quoteCurrency) EditText mQuoteCurrency;
    @BindView(R.id.calculateButton) Button mCalculate;
    @BindView(R.id.subheading) TextView mSubheading;
    @BindView(R.id.rate) TextView mRate;
    @BindView(R.id.viewCurrencies) Button mViewCurrenciesButton;

    List<String> individualCurrency = new ArrayList<>();
    private String mRecentBaseCurrency;
    private String mRecentQuoteCurrency;

    // Preference manager
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCurrencyPair = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CURRENCY_PAIR);

        mCurrencyPairListener = mCurrencyPair.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot currencySnapshot : snapshot.getChildren()){
                    String currency = currencySnapshot.getValue().toString();
                    Log.d("Currencies updated", "currency: " + currency);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        ButterKnife.bind(this);
        mCalculate.setOnClickListener(this);
        mViewCurrenciesButton.setOnClickListener(this);

        mSubheading.setText(R.string.subheading);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentBaseCurrency = mSharedPreferences.getString(Constants.PREFERENCES_BASE_CURRENCY, null);
        mRecentQuoteCurrency = mSharedPreferences.getString(Constants.PREFERENCES_QUOTE_CURRENCY, null);

        mBaseCurrency.setText(mRecentBaseCurrency);
        mQuoteCurrency.setText(mRecentQuoteCurrency);

        mEditor = mSharedPreferences.edit();

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String userName = intent.getStringExtra("username");

        mUser.setText("Welcome " + firstName);

        mUser.setText("Welcome " + userName);

        mCurrencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currency = ((TextView) view).getText().toString();
                Toast.makeText(Converter.this, currency.substring(0, 7), Toast.LENGTH_LONG).show();
                Uri link = Uri.parse("https://www.google.com/search?q=" + currency.substring(0, 7));
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH, link);

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(Converter.this, "Unable to locate activity" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ForexExchangeApi client = ForexExchangeClient.getClient();
        Call<USDRateResponse> call = client.getCurrencies(Constants.EXCHANGE_RATE_API_KEY, "USD");

        call.enqueue(new Callback<USDRateResponse>() {
            @Override
            public void onResponse(Call<USDRateResponse> call, Response<USDRateResponse> response) {
                if (response.isSuccessful()){
                    ConversionRates currenciesObject = response.body().getConversionRates();

                    mRate.setText("Rate: " );

                    individualCurrency.add("EUR/USD - " + currenciesObject.getEur().toString());
                    individualCurrency.add("AUD/USD - " + currenciesObject.getAud().toString());
                    individualCurrency.add("GBP/USD - " + currenciesObject.getGbp().toString());
                    individualCurrency.add("USD/CAD - " + currenciesObject.getCad().toString());
                    individualCurrency.add("USD/JPY - " + currenciesObject.getJpy().toString());
                    individualCurrency.add("USD/CHF - " + currenciesObject.getChf().toString());
                    individualCurrency.add("NZD/USD - " + currenciesObject.getNzd().toString());

                    ArrayAdapter adapter = new ArrayAdapter(Converter.this, android.R.layout.simple_list_item_1, individualCurrency);
                    mCurrencyList.setAdapter(adapter);
                } else{
                    Toast.makeText(Converter.this, "Something went wrong. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<USDRateResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == mCalculate){
            String baseCurrency = mBaseCurrency.getText().toString();
            String quoteCurrency = mQuoteCurrency.getText().toString();

            if(!(baseCurrency.isEmpty() && quoteCurrency.isEmpty())){
                saveCurrencyToFirebase(baseCurrency, quoteCurrency);
                addToSharedPreferences(baseCurrency, quoteCurrency);
                Toast.makeText(Converter.this, baseCurrency + "/" + quoteCurrency + " saved to history in database", Toast.LENGTH_LONG).show();
            }
        }

        if(view == mViewCurrenciesButton){
            Intent intent = new Intent(Converter.this, SavedCurrencyListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Converter.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mCurrencyPair.removeEventListener(mCurrencyPairListener);
    }

    public void addToSharedPreferences(String base, String quote){
        mEditor.putString(Constants.PREFERENCES_BASE_CURRENCY, base).apply();
        mEditor.putString(Constants.PREFERENCES_QUOTE_CURRENCY, quote).apply();
    }

    public void saveCurrencyToFirebase(String base, String quote){
        String currencyPair = base + "/" + quote;
        mCurrencyPair.push().setValue(currencyPair);
    }
}
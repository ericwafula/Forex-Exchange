package com.moringaschool.forexexchange.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.forexexchange.R;
import com.moringaschool.forexexchange.adapters.FirebaseCurrencyViewHolder;
import com.moringaschool.forexexchange.network.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCurrencyListActivity extends AppCompatActivity {
    private DatabaseReference mCurrencyReference;
    private FirebaseRecyclerAdapter<String, FirebaseCurrencyViewHolder> mFirebaseAdapter;
    public static final String TAG = SavedCurrencyListActivity.class.getSimpleName();

    @BindView(R.id.savedCurrenciesRecyclerView) RecyclerView mSavedCurrenciesRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.savedCurrenciesHeading) TextView mSavedCurrenciesHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_currency_list);

        ButterKnife.bind(this);

        mCurrencyReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CURRENCY_PAIR);
        setupFirebaseAdapter();
        Log.d(TAG, "Unable to to setup firebase adapter");
        hideProgressBar();
        showRestaurants();
    }

    private void setupFirebaseAdapter(){
        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(mCurrencyReference, String.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<String, FirebaseCurrencyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseCurrencyViewHolder holder, int position, @NonNull String model) {
                holder.bindCurrency(model);
            }

            @NonNull
            @Override
            public FirebaseCurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_list_item, parent, false);
                return new FirebaseCurrencyViewHolder(view);
            }
        };
        mSavedCurrenciesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSavedCurrenciesRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showRestaurants() {
        mSavedCurrenciesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
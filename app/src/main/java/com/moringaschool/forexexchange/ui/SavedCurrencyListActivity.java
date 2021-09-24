package com.moringaschool.forexexchange.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.moringaschool.forexexchange.adapters.FirebaseCurrencyListAdapter;
import com.moringaschool.forexexchange.adapters.FirebaseCurrencyViewHolder;
import com.moringaschool.forexexchange.network.Constants;
import com.moringaschool.forexexchange.util.OnStartDragListener;
import com.moringaschool.forexexchange.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCurrencyListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mCurrencyReference;
    private FirebaseRecyclerAdapter<String, FirebaseCurrencyViewHolder> mFirebaseRecyclerAdapter;
    public static final String TAG = SavedCurrencyListActivity.class.getSimpleName();

    FirebaseCurrencyListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

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

        mFirebaseAdapter = new FirebaseCurrencyListAdapter(options, mCurrencyReference, (OnStartDragListener) this, this) {
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
        mSavedCurrenciesRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mSavedCurrenciesRecyclerView);
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

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
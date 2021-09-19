package com.moringaschool.forexexchange.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.forexexchange.ConversionRates;
import com.moringaschool.forexexchange.R;
import com.moringaschool.forexexchange.network.Constants;
import com.moringaschool.forexexchange.ui.SavedCurrencyListActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseCurrencyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCurrency(String currency){
        TextView savedCurrency = (TextView) mView.findViewById(R.id.savedCurrency);

        savedCurrency.setText(currency);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<String> currencies = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CURRENCY_PAIR);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    currencies.add(dataSnapshot.getValue(String.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, SavedCurrencyListActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("currencies", Parcels.wrap(currencies));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

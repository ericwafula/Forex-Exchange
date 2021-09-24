package com.moringaschool.forexexchange.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.forexexchange.util.ItemTouchHelperAdapter;
import com.moringaschool.forexexchange.util.OnStartDragListener;

public class FirebaseCurrencyListAdapter extends FirebaseRecyclerAdapter<String, FirebaseCurrencyViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseCurrencyListAdapter(@NonNull FirebaseRecyclerOptions<String> options,
                                       DatabaseReference ref,
                                       OnStartDragListener onStartDragListener,
                                       Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull FirebaseCurrencyViewHolder holder, int position, @NonNull String model) {

    }

    @NonNull
    @Override
    public FirebaseCurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public boolean onItemMove(int fromPosition, int ToPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}

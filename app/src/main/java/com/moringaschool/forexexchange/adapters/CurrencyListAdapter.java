package com.moringaschool.forexexchange.adapters;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.forexexchange.ConversionRates;
import com.moringaschool.forexexchange.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder> {
    private List<ConversionRates> mConversionRates;
    private Context mContext;

    public CurrencyListAdapter(Context mContext,  List<ConversionRates> mConversionRates) {
        this.mConversionRates = mConversionRates;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CurrencyListAdapter.CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_list_item, parent, false);
//        CurrencyViewHolder viewHolder = new CurrencyViewHolder(view);
//        return viewHolder;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyListAdapter.CurrencyViewHolder holder, int position) {
        holder.bindCurrency(mConversionRates.get(position));
    }

    @Override
    public int getItemCount() {
        return mConversionRates.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder{
        @BindView(com.moringaschool.forexexchange.R.id.currencyView) TextView mCurrencyView;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCurrency(ConversionRates conversionRates){
            mCurrencyView.setText(conversionRates.getUsd());
        }
    }
}

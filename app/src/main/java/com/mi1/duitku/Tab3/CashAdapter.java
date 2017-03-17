package com.mi1.duitku.Tab3;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mi1.duitku.R;

import java.util.ArrayList;

/**
 * Created by owner on 3/7/2017.
 */

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.ViewHolder> {

    Context context;
    ArrayList<CashInfo> cashInfos;

    public CashAdapter(Context context, ArrayList<CashInfo> cashInfos) {
        this.context = context;
        this.cashInfos = cashInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_cash, parent, false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CashInfo item = cashInfos.get(position);
        holder.amount.setText("Rp "+item.amount);
        holder.date.setText(item.date);
    }

    @Override
    public int getItemCount() {
        return cashInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount;
        TextView date;

        public ViewHolder(View v) {
            super(v);
            amount = (TextView) v.findViewById(R.id.txt_amount);
            date = (TextView) v.findViewById(R.id.txt_date);
        }
    }
}
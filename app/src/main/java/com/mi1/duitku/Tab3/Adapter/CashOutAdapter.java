package com.mi1.duitku.Tab3.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mi1.duitku.Common.CommonFunction;
import com.mi1.duitku.R;
import com.mi1.duitku.Tab3.Common.CashInfo;
import com.mi1.duitku.Tab3.Common.Tab3Global;
import com.mi1.duitku.Tab3.DetailTransactionActivity;

/**
 * Created by owner on 3/7/2017.
 */

public class CashOutAdapter extends RecyclerView.Adapter<CashOutAdapter.ViewHolder> {

    Context context;

    public CashOutAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_cash, parent, false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CashInfo.TransactionList item = Tab3Global._cashOutData.get(position);
        holder.tvAmount.setText(CommonFunction.formatNumbering(item.amount));
        holder.tvDate.setText(CommonFunction.getFormatedDate(item.date));
        holder.llCashInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTransactionActivity.class);
                intent.putExtra("index", 1);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Tab3Global._cashOutData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount;
        TextView tvDate;
        LinearLayout llCashInfo;

        public ViewHolder(View v) {
            super(v);
            tvAmount = (TextView) v.findViewById(R.id.txt_amount);
            tvDate = (TextView) v.findViewById(R.id.txt_date);
            llCashInfo = (LinearLayout) v.findViewById(R.id.ll_cash_info);
        }
    }
}

package com.example.moneymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.model.TransactionStatistic;

import java.util.List;

public class AdapterRcStatistic extends RecyclerView.Adapter<AdapterRcStatistic.ViewHolder> {

    private List<TransactionStatistic> transactionStatistics;

    public AdapterRcStatistic(List<TransactionStatistic> transactionStatistics) {
        this.transactionStatistics = transactionStatistics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_month.setText("Tháng " + transactionStatistics.get(position).getMonth());
        holder.txt_value.setText(transactionStatistics.get(position).getValue() + " VND");
    }

    @Override
    public int getItemCount() {
        return transactionStatistics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_month, txt_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_month = itemView.findViewById(R.id.txt_month);
            txt_value = itemView.findViewById(R.id.txt_value);
        }
    }
}

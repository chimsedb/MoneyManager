package com.example.moneymanager.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.persistence.Transaction;

import java.util.List;

public class AdapterRCFuture extends RecyclerView.Adapter<AdapterRCFuture.ViewHolder> {

    private List<Transaction> transactions;

    public AdapterRCFuture(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!transactions.get(position).getDateCreate().equals("")){
            String temp = transactions.get(position).getDateCreate();
            String day = temp.substring(0,temp.lastIndexOf(","));
            String date =temp.substring(temp.lastIndexOf(" ")+1);
            holder.txt_day_number.setText(date.substring(0,2));
            holder.txt_day.setText(day);
            holder.txt_year.setText("Tháng "+date.substring(3,5)
                    +", 20"
                    +date.substring(6,8));
        }

        holder.txt_service.setText(transactions.get(position).getService());
        holder.txt_note.setText(transactions.get(position).getNote());
        holder.txt_cost.setText(transactions.get(position).getWallet());
        Log.d("123123",transactions.get(position).getImage()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(transactions.get(position).getImage(), 0, transactions.get(position).getImage().length);
        holder.iv_service.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_day_number,txt_day,txt_year,txt_service,txt_note,txt_cost;
        ImageView iv_service;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_day_number = itemView.findViewById(R.id.txt_day_number);
            txt_day = itemView.findViewById(R.id.txt_day);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_service = itemView.findViewById(R.id.txt_service);
            txt_note = itemView.findViewById(R.id.txt_note);
            txt_cost = itemView.findViewById(R.id.txt_cost);
            iv_service = itemView.findViewById(R.id.iv_service);
        }
    }
}

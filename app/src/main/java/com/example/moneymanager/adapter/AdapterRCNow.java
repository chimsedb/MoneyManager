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
import com.example.moneymanager.callback.IRecyclerCostViewClickListener;
import com.example.moneymanager.callback.IRecyclerViewLongClickListener;
import com.example.moneymanager.eventBus.NowItemClick;
import com.example.moneymanager.eventBus.NowItemLongClick;
import com.example.moneymanager.persistence.Transaction;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AdapterRCNow extends RecyclerView.Adapter<AdapterRCNow.ViewHolder> {

    private List<Transaction> transactions;
    List<Boolean> listStatus;

    public AdapterRCNow(List<Transaction> transactions) {
        this.transactions = transactions;
        listStatus = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        for (int i = 0; i < transactions.size(); i++) {
            listStatus.add(false);
        }
        if (!transactions.get(position).getDateCreate().equals("")) {
            String temp = transactions.get(position).getDateCreate();
            String day = temp.substring(0, temp.lastIndexOf(","));
            String date = temp.substring(temp.lastIndexOf(" ") + 1);
            holder.txt_day_number.setText(date.substring(0, 2));
            holder.txt_day.setText(day);
            holder.txt_year.setText("Tháng " + date.substring(3, 5)
                    + ", 20"
                    + date.substring(6, 8));
        }

        holder.txt_service.setText(transactions.get(position).getService());
        holder.txt_note.setText(transactions.get(position).getNote());
        holder.txt_cost.setText(transactions.get(position).getWallet());
        Log.d("123123", transactions.get(position).getImage() + "");
        holder.bitmap = BitmapFactory.decodeByteArray(transactions.get(position).getImage(), 0, transactions.get(position).getImage().length);
        holder.iv_service.setImageBitmap(holder.bitmap);

        holder.setLongClickListener(new IRecyclerViewLongClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                holder.status = !holder.status;
                if (holder.status == true) {
                    holder.iv_service.setImageResource(R.drawable.ic_check_circle_blue);
                } else {
                    holder.iv_service.setImageBitmap(holder.bitmap);
                }
                listStatus.set(position, holder.status);
                Log.d("!23123", position + "");
                EventBus.getDefault().postSticky(new NowItemLongClick(true, listStatus, transactions));
            }
        });

        holder.setClickListener(new IRecyclerCostViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (holder.status == true) {
                    holder.iv_service.setImageBitmap(holder.bitmap);
                    holder.status = false;
                    listStatus.set(position, false);
                    EventBus.getDefault().postSticky(new NowItemClick(true, listStatus));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        TextView txt_day_number, txt_day, txt_year, txt_service, txt_note, txt_cost;
        ImageView iv_service;
        IRecyclerViewLongClickListener longClickListener;
        IRecyclerCostViewClickListener clickListener;
        Bitmap bitmap;
        boolean status = false;

        public void setLongClickListener(IRecyclerViewLongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }

        public void setClickListener(IRecyclerCostViewClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_day_number = itemView.findViewById(R.id.txt_day_number);
            txt_day = itemView.findViewById(R.id.txt_day);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_service = itemView.findViewById(R.id.txt_service);
            txt_note = itemView.findViewById(R.id.txt_note);
            txt_cost = itemView.findViewById(R.id.txt_cost);
            iv_service = itemView.findViewById(R.id.iv_service);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            longClickListener.onItemClickListener(view, getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClickListener(view, getAdapterPosition());
        }
    }
}

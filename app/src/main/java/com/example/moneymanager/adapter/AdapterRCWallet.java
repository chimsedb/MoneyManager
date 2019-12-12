package com.example.moneymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.callback.IRecyclerViewClickListener;
import com.example.moneymanager.callback.IRecyclerViewLongClickListener;
import com.example.moneymanager.persistence.entities.Wallet;


public class AdapterRCWallet extends ListAdapter<Wallet,AdapterRCWallet.ViewHolder> {


    public AdapterRCWallet() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Wallet> DIFF_CALLBACK = new DiffUtil.ItemCallback<Wallet>() {
        @Override
        public boolean areItemsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return (oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getCost().equals(newItem.getCost()));
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tv_name.setText(getItem(0).getName());
        holder.tv_cost.setText(getItem(0).getCost());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView tv_name, tv_cost;
        IRecyclerViewLongClickListener longClickListener;
        IRecyclerViewClickListener clickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_cost = itemView.findViewById(R.id.tv_cost);

        }

    }
}

package com.example.moneymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.callback.IRecyclerViewClickListener;
import com.example.moneymanager.eventBus.CostItemClick;
import com.example.moneymanager.eventBus.RevenueItemClick;
import com.example.moneymanager.model.Revenue;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class AdapterRCRevenue extends RecyclerView.Adapter<AdapterRCRevenue.ViewHolder> {

    private FragmentActivity activity;
    private List<Revenue> revenues;

    public AdapterRCRevenue(FragmentActivity activity, List<Revenue> revenues) {
        this.activity = activity;
        this.revenues = revenues;
    }

    @NonNull
    @Override
    public AdapterRCRevenue.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debets, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRCRevenue.ViewHolder holder, int position) {
        holder.imv_debets.setImageResource(revenues.get(position).getImage());
        holder.txt_debets.setText(revenues.get(position).getName());
        holder.setListener(new IRecyclerViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                EventBus.getDefault().postSticky(new RevenueItemClick(true,revenues.get(position),false,false,true));
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return revenues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imv_debets;
        TextView txt_debets;
        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_debets = itemView.findViewById(R.id.imv_debets);
            txt_debets = itemView.findViewById(R.id.txt_debets);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view,getAdapterPosition());
        }
    }
}

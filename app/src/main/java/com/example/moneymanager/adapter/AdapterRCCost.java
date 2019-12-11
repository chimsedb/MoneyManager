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
import com.example.moneymanager.callback.IRecyclerCostViewClickListener;
import com.example.moneymanager.eventBus.CostItemClick;
import com.example.moneymanager.model.Cost;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class AdapterRCCost extends RecyclerView.Adapter<AdapterRCCost.ViewHolder> {
    private FragmentActivity activity;
    private List<Cost> costs;

    public AdapterRCCost(List<Cost> costs,FragmentActivity activity) {
        this.costs = costs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterRCCost.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost, parent, false);
        AdapterRCCost.ViewHolder viewHolder = new AdapterRCCost.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRCCost.ViewHolder holder, int position) {
        holder.imv_costs.setImageResource(costs.get(position).getImage());
        holder.txt_costs.setText(costs.get(position).getName());
        holder.setListener(new IRecyclerCostViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                EventBus.getDefault().postSticky(new CostItemClick(true,costs.get(position),false,false,false));
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return costs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imv_costs;
        TextView txt_costs;
        IRecyclerCostViewClickListener listener;

        public void setListener(IRecyclerCostViewClickListener listener) {
            this.listener = listener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_costs = itemView.findViewById(R.id.imv_costs);
            txt_costs = itemView.findViewById(R.id.txt_costs);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view,getAdapterPosition());
        }
    }
}

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
import com.example.moneymanager.eventBus.DebetItemClick;
import com.example.moneymanager.model.Debet;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class AdapterRCDebets extends RecyclerView.Adapter<AdapterRCDebets.ViewHolder> {

    private FragmentActivity activity;
    private List<Debet> debets;

    public AdapterRCDebets(FragmentActivity activity, List<Debet> debets) {
        this.activity = activity;
        this.debets = debets;
    }

    @NonNull
    @Override
    public AdapterRCDebets.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debets, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRCDebets.ViewHolder holder, int position) {
        holder.imv_debets.setImageResource(debets.get(position).getImage());
        holder.txt_debets.setText(debets.get(position).getName());
        holder.setListener(new IRecyclerViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                boolean statusAdd = getStatusAddDebet(position);
                EventBus.getDefault().postSticky(new DebetItemClick(true,debets.get(position),!statusAdd,statusAdd,statusAdd));
                activity.finish();
            }
        });
    }

    private boolean getStatusAddDebet(int position) {
        switch (position){
            case 0 :
                return false;
            case 1 :
                return true;
            case 2 :
                return true;
            case 3 :
                return false;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return debets.size();
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

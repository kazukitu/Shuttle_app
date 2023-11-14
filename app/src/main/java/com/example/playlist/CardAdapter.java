package com.example.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context context;
    private List<CardItem> itemList;

    public CardAdapter(Context context, List<CardItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.typeTextView.setText(item.getType());
        holder.yearReleasedTextView.setText(String.valueOf(item.getYearReleased()));
        holder.priceUSDTextView.setText(String.valueOf(item.getPriceUSD()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView yearReleasedTextView;
        TextView priceUSDTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.card_name);
            typeTextView = itemView.findViewById(R.id.card_type);
            yearReleasedTextView = itemView.findViewById(R.id.card_year_released);
            priceUSDTextView = itemView.findViewById(R.id.card_price);
        }
    }
}

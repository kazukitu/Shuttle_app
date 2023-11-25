package com.example.playlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        // Check if the imageResource string is not null before trying to replace or use it
        if (item.getImageResource() != null && !item.getImageResource().isEmpty()) {
            String resourceName = item.getImageResource().replace("R.drawable.", "");
            int imageResId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (imageResId != 0) { // Check if the resource ID is valid
                holder.imageView.setImageResource(imageResId);
            } else {
                // Set a default image if the resource ID is not valid
                holder.imageView.setImageResource(R.drawable.playstation5);
            }
        } else {
            // Set a default image if the imageResource string is null
            holder.imageView.setImageResource(R.drawable.playstation5);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("type", item.getType());
                intent.putExtra("yearReleased", item.getYearReleased());
                intent.putExtra("priceUSD", item.getPriceUSD());

                String resourceName = item.getImageResource().replace("R.drawable.", "");
                int imageResId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
                if (imageResId != 0) {
                    intent.putExtra("imageResId", imageResId);
                }

                context.startActivity(intent);
            }
        });
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
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.card_name);
            typeTextView = itemView.findViewById(R.id.card_type);
            yearReleasedTextView = itemView.findViewById(R.id.card_year_released);
            priceUSDTextView = itemView.findViewById(R.id.card_price);
            imageView = itemView.findViewById(R.id.card_image);
        }
    }
}

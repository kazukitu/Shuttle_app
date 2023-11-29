package com.example.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingCartActivity extends AppCompatActivity {

    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView feeTextView;
    private TextView totalTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        productNameTextView = findViewById(R.id.text_view_cart_title);
        productPriceTextView = findViewById(R.id.text_view_subtotal);
        feeTextView = findViewById(R.id.text_view_fees);
        totalTextView = findViewById(R.id.text_view_total);
        checkoutButton = findViewById(R.id.button_checkout);

        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        int imageResId = intent.getIntExtra("imageResId", 0);

        productNameTextView.setText("Confirm Order: " + productName);
        productPriceTextView.setText("Price: " + productPrice);
        totalTextView.setText("Total:" + productPrice);
        feeTextView.setText("Fees Delivery: $10.00");

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use the current Activity's context to create the Intent
                Intent intent = new Intent(v.getContext(), com.example.playlist.ConfirmationActivity.class);
                // Start the new activity using the current context
                v.getContext().startActivity(intent);
            }
        });
    }
}

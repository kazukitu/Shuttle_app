package com.example.playlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

public class DetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName;
    private TextView productType;
    private TextView productYear;
    private TextView productPrice;
    private RadioGroup packageOptions;
    private Button buyButton;
    private Button backButton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        productImage = findViewById(R.id.image_product);
        productName = findViewById(R.id.text_product_name);
        productType = findViewById(R.id.text_product_type);
        productYear = findViewById(R.id.text_product_year);
        productPrice = findViewById(R.id.text_product_price);
        packageOptions = findViewById(R.id.radio_group);
        buyButton = findViewById(R.id.button_buy);
        backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();
        if (intent != null) {
            productName.setText(intent.getStringExtra("name"));
            productType.setText(intent.getStringExtra("type"));
            int yearReleased = intent.getIntExtra("yearReleased", 0);
            productYear.setText(yearReleased > 0 ? Integer.toString(yearReleased) : "N/A");
            double price = intent.getDoubleExtra("priceUSD", 0);
            productPrice.setText(String.format("$%.2f", price));

            int imageResId = intent.getIntExtra("imageResId", 0);
            if (imageResId != 0) {
                productImage.setImageResource(imageResId);
            } else {
                productImage.setImageResource(R.drawable.playstation5);
            }
        }
        packageOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedOption = findViewById(checkedId);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShoppingCartActivity.class);
                int imageResId = intent.getIntExtra("imageResId", 0);
                intent.putExtra("productName", productName.getText().toString());
                intent.putExtra("productPrice", productPrice.getText().toString());
                intent.putExtra("imageResId", imageResId);


                v.getContext().startActivity(intent);
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}

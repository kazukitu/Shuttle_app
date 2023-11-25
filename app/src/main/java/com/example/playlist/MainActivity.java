package com.example.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String json = "// 20231114221348\n" +
                "// http://15.168.113.223:8080/api/items\n" +
                "\n" +
                "[\n" +
                "  {\n" +
                "    \"name\": \"PlayStation 5\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2020,\n" +
                "    \"priceUSD\": 499,\n" +
                "    \"imageURL\": \"playstation5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Xbox Series X\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2020,\n" +
                "    \"priceUSD\": 499,\n" +
                "    \"imageURL\": \"xbox\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Nintendo Switch\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2017,\n" +
                "    \"priceUSD\": 299,\n" +
                "    \"imageURL\": \"switch_pic\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"PlayStation 4\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2013,\n" +
                "    \"priceUSD\": 399,\n" +
                "    \"imageURL\": \"playstation4\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Xbox One\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2013,\n" +
                "    \"priceUSD\": 499,\n" +
                "    \"imageURL\": \"xbox_one\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Alienware\",\n" +
                "    \"type\": \"PC\",\n" +
                "    \"yearReleased\": 2023,\n" +
                "    \"priceUSD\": 999,\n" +
                "    \"imageURL\": \"pc\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Nintendo 64\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1996,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"n64\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Sega Genesis\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1989,\n" +
                "    \"priceUSD\": 189,\n" +
                "    \"imageURL\": \"genesis\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Super Nintendo Entertainment System\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1990,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"sfc\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"PlayStation 2\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2000,\n" +
                "    \"priceUSD\": 299,\n" +
                "    \"imageURL\": \"playstation2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Xbox 360\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2005,\n" +
                "    \"priceUSD\": 299,\n" +
                "    \"imageURL\": \"xbox_360\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Wii\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2006,\n" +
                "    \"priceUSD\": 249,\n" +
                "    \"imageURL\": \"wii\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Nintendo GameCube\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 2001,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"game_cube\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Nintendo Entertainment System\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1985,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"fc\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Sega Dreamcast\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1999,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"dreamcast\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Sony PSP\",\n" +
                "    \"type\": \"Portable\",\n" +
                "    \"yearReleased\": 2004,\n" +
                "    \"priceUSD\": 249,\n" +
                "    \"imageURL\": \"psp\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Game Boy Advance\",\n" +
                "    \"type\": \"Portable\",\n" +
                "    \"yearReleased\": 2001,\n" +
                "    \"priceUSD\": 99,\n" +
                "    \"imageURL\": \"gameboy\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Atari 2600\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1977,\n" +
                "    \"priceUSD\": 199,\n" +
                "    \"imageURL\": \"atari\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Neo Geo\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1990,\n" +
                "    \"priceUSD\": 649,\n" +
                "    \"imageURL\": \"neo_geo\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Sega Saturn\",\n" +
                "    \"type\": \"Console\",\n" +
                "    \"yearReleased\": 1994,\n" +
                "    \"priceUSD\": 399,\n" +
                "    \"imageURL\": \"saturn\"\n" +
                "  }\n" +
                "]";

        Gson gson = new Gson();
        CardItem[] cardItems = gson.fromJson(json, CardItem[].class);
        List<CardItem> cardItemList = Arrays.asList(cardItems);

        cardAdapter = new CardAdapter(this, cardItemList);
        recyclerView.setAdapter(cardAdapter);


        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                // Start the LoginActivity
                startActivity(intent);
                // Finish the current activity (MainActivity)
                finish();
            }
        });
    }
}

package com.example.playlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Fetch the JSON data from the server
        new FetchItemsTask().execute("http://15.168.113.223:7070/api/items");

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class FetchItemsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urls[0]).build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                Gson gson = new Gson();
                CardItem[] cardItems = gson.fromJson(json, CardItem[].class);
                List<CardItem> cardItemList = Arrays.asList(cardItems);
                cardAdapter = new CardAdapter(MainActivity.this, cardItemList);
                recyclerView.setAdapter(cardAdapter);
            }
        }
    }
}

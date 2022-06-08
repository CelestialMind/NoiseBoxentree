package com.example.noiseboxentree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecView;
    private NoiseBoxItemRecViewAdapter adapter;

    // Database declaration
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        adapter = new NoiseBoxItemRecViewAdapter(this, "activity_cart");
        cartRecView = findViewById(R.id.cartRecView);

        cartRecView.setAdapter(adapter);
        cartRecView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        ArrayList<NoiseBox> noiseBoxes = new ArrayList<>();
        noiseBoxes = databaseHelper.getEverything("CUSTOM_NOISE_BOX_TABLE");
//        noiseBoxes.add( new NoiseBox(1, "custom", 12, 12, 12,true, true));
        adapter.setNoiseBoxes(noiseBoxes);
    }
}
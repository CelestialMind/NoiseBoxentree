package com.example.noiseboxentree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRectView;
    private CartItemRecViewAdapter adapter;

    // Database declaration
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        adapter = new CartItemRecViewAdapter(this);
        cartRectView = findViewById(R.id.cartRecView);

        cartRectView.setAdapter(adapter);
        cartRectView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        ArrayList<NoiseBox> noiseBoxes = new ArrayList<>();
//        noiseBoxes.add(new NoiseBox(1, "ultra turbo mega thing", 115, 100, 50, true, true));
        noiseBoxes = databaseHelper.getEverything();
        adapter.setNoiseBoxes(noiseBoxes);
    }
}
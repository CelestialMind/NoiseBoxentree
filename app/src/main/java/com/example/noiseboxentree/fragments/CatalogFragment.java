package com.example.noiseboxentree.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noiseboxentree.NoiseBoxItemRecViewAdapter;
import com.example.noiseboxentree.DatabaseHelper;
import com.example.noiseboxentree.NoiseBox;
import com.example.noiseboxentree.R;

import java.util.ArrayList;


public class CatalogFragment extends Fragment {

    private RecyclerView catalogRecView;
    private NoiseBoxItemRecViewAdapter adapter;
    // Database declaration
    DatabaseHelper databaseHelper;

    public CatalogFragment() {
        // Required empty public constructor
        super(R.layout.fragment_catalog);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iView = inflater.inflate(R.layout.fragment_catalog, container, false);

        adapter = new NoiseBoxItemRecViewAdapter(CatalogFragment.this.getContext(), "fragment_catalog");
        catalogRecView = iView.findViewById(R.id.catalogRecView);

        catalogRecView.setAdapter(adapter);
        catalogRecView.setLayoutManager(new LinearLayoutManager(CatalogFragment.this.getContext()));

        databaseHelper = new DatabaseHelper(CatalogFragment.this.getContext());
        ArrayList<NoiseBox> noiseBoxes = new ArrayList<>();
        noiseBoxes = databaseHelper.getEverything("CATALOG_NOISE_BOX_TABLE");
//        noiseBoxes.add( new NoiseBox(1, "miner", 12, 12, 12,true, true));
        adapter.setNoiseBoxes(noiseBoxes);

        return iView;
    }
}
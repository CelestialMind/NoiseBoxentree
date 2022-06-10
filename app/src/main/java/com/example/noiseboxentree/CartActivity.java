package com.example.noiseboxentree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecView;
    private NoiseBoxItemRecViewAdapter adapter;
    private MaterialButton btnBuy;
    Dialog dialog;
    EditText edtDialogName;
    MaterialButton btnDialogDrder;
    ImageView imgDialogDelete;

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

        btnBuy = findViewById(R.id.btnCartPageBuy);

        btnBuy.setOnClickListener(view -> {
            callLoginDialog();
        });

    }

    private void callLoginDialog()
    {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.order_dialog);
        dialog.setCancelable(false);
        btnDialogDrder = dialog.findViewById(R.id.btnOrderDialogOrder);
        imgDialogDelete = dialog.findViewById(R.id.imgOrderDialogDelete);

        edtDialogName = dialog.findViewById(R.id.edtOrderDialogNameRequest);
        dialog.show();

        btnDialogDrder.setOnClickListener(view -> {
            Toast.makeText(CartActivity.this, "Say hello to my little friend!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        imgDialogDelete.setOnClickListener(view -> dialog.dismiss());

    }
}
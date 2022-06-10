package com.example.noiseboxentree.cartactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noiseboxentree.DatabaseHelper;
import com.example.noiseboxentree.NoiseBox;
import com.example.noiseboxentree.NoiseBoxItemRecViewAdapter;
import com.example.noiseboxentree.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecView;
    private NoiseBoxItemRecViewAdapter adapter;
    private MaterialButton btnOpenOrderForm;
    Dialog dialog;
    EditText edtDialogName;
    MaterialButton btnDialogOrder;
    ImageView imgDialogDelete;
    FrameLayout activityLayout;

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

        btnOpenOrderForm = findViewById(R.id.btnCartPageOpenOrderForm);

        btnOpenOrderForm.setOnClickListener(view -> {
//            callLoginDialog();
            startActivity(new Intent(CartActivity.this, OrderForm.class));
            activityLayout.getForeground().setAlpha( 200);
        });

        activityLayout = findViewById( R.id.cart_view_page_foregrounder);
        activityLayout.getForeground().setAlpha( 0);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        activityLayout = findViewById( R.id.cart_view_page_foregrounder);
        activityLayout.getForeground().setAlpha( 0);
    }

    private void callLoginDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.order_dialog);
        dialog.setCancelable(false);
        btnDialogOrder = dialog.findViewById(R.id.btnOrderDialogOrder);
        imgDialogDelete = dialog.findViewById(R.id.imgOrderDialogDelete);

        edtDialogName = dialog.findViewById(R.id.edtOrderDialogName);
        dialog.show();

        btnDialogOrder.setOnClickListener(view -> {
            Toast.makeText(CartActivity.this, "Say hello to my little friend!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        imgDialogDelete.setOnClickListener(view -> dialog.dismiss());

    }
}
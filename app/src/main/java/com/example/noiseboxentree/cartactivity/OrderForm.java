package com.example.noiseboxentree.cartactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.example.noiseboxentree.R;

public class OrderForm extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_form);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

//        getWindow().setLayout((int)(width*.8), (int)(height*.5));
        getWindow().setLayout(width, height);
    }
}

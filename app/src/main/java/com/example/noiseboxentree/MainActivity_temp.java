package com.example.noiseboxentree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity_temp extends AppCompatActivity {

    // View elements declaration
    EditText edtCustomPageLength, edtCustomPageWidth, edtCustomPageHeight;
    SwitchMaterial swCustomPageAdvSp, swCustomPageFan;
    Button btnCustomPageAdd;
    FloatingActionButton fABtnOpenCart;

    // Database declaration
    DatabaseHelper databaseHelper;

    @Override //------------------------------------------------------------------------------------
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);

        databaseHelper = new DatabaseHelper(this);

        initView(); // initializes the activity_main elements

        fABtnOpenCart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity_temp.this, CartActivity.class);
            startActivity(intent);

        });

        // Data retrieving button of the Custom page is clicked
        btnCustomPageAdd.setOnClickListener(view -> {
            NoiseBox noiseBox = readerFromCustomPage(); // initializes NoiseBox instance using prepared function
            if (null == noiseBox){
                // when returned from readerFromCustomPage() is null, hence the input was invalid
                Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT).show();
            }else{
                // when input was ok
                Toast.makeText(this, noiseBox.toString(), Toast.LENGTH_SHORT).show();
                if (databaseHelper.addOne(noiseBox)); // finally adds the entered data to the Database
                else Toast.makeText(this, "But, how?", Toast.LENGTH_SHORT).show(); // to hell with all of it
            }
        });

    }//---------------------------------------------------------------------------------------------

    // Retrieves data from the Custom Page form
    // returns prepared NoiseBox object
    private NoiseBox readerFromCustomPage() {
        boolean isAllCool = true; // Will tell if all entered values are correct

        // had to initialize them because they may not be executed in the Try block
        int length = 0, width = 0, height = 0;

        // Tries to retrieve innerLength, innerWidth, innerHeight
        try{
            length = Integer.parseInt(edtCustomPageLength.getText().toString());
            width = Integer.parseInt(edtCustomPageWidth.getText().toString());
            height = Integer.parseInt(edtCustomPageHeight.getText().toString());
        }catch (Exception e){
            isAllCool = false;  // when the input is invalid
        }

        boolean sp = swCustomPageAdvSp.isChecked();
        boolean fan = swCustomPageFan.isChecked();

        // if all is correct returns an instance of NoiseBox with retrieved data
        if (isAllCool) return new NoiseBox(-1, "custom", length, width, height, sp, fan);
        // else
        return null;
    }

    // initializes the activity_main elements
    private void initView(){
        edtCustomPageLength = findViewById(R.id.edtCustomPageLength);
        edtCustomPageWidth = findViewById(R.id.edtCustomPageWidth);
        edtCustomPageHeight = findViewById(R.id.edtCustomPageHeight);
        swCustomPageAdvSp = findViewById(R.id.swCustomPageAdvSp);
        swCustomPageFan = findViewById(R.id.swCustomPageFan);
        btnCustomPageAdd = findViewById(R.id.btnCustomPageAdd);
        fABtnOpenCart = findViewById(R.id.fABtnOpenCart);
    }



}
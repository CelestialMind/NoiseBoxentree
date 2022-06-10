package com.example.noiseboxentree.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noiseboxentree.DatabaseHelper;
import com.example.noiseboxentree.NoiseBox;
import com.example.noiseboxentree.R;
import com.google.android.material.switchmaterial.SwitchMaterial;


public class CustomFragment extends Fragment {

    // View elements declaration
    EditText edtCustomPageLength, edtCustomPageWidth, edtCustomPageHeight;
    SwitchMaterial swCustomPageAdvSp, swCustomPageFan;
    Button btnCustomPageAdd;
//    FloatingActionButton fABtnOpenCart;

    // Database declaration
    DatabaseHelper databaseHelper;

    public CustomFragment() {
        super(R.layout.fragment_custom);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iView = inflater.inflate(R.layout.fragment_custom, container, false);

        initView(iView);

        databaseHelper = new DatabaseHelper(this.getActivity());


//        fABtnOpenCart.setOnClickListener(view -> {
//            Intent intent = new Intent(CustomFragment.this.getActivity(), CartActivity.class);
//            startActivity(intent);
//        });

        // Data retrieving button of the Custom page is clicked
        btnCustomPageAdd.setOnClickListener(view -> {
            NoiseBox noiseBox = readerFromCustomPage(); // initializes NoiseBox instance using prepared function
            if (null == noiseBox){
                // when returned from readerFromCustomPage() is null, hence the input was invalid
                Toast.makeText(CustomFragment.this.getContext(), "Incorrect input", Toast.LENGTH_SHORT).show();
            }else{
                // when input was ok
                if (!databaseHelper.addOne(noiseBox, "CUSTOM_NOISE_BOX_TABLE")) // finally adds the entered data to the Database
                    Toast.makeText(CustomFragment.this.getContext(), "But, how?", Toast.LENGTH_SHORT).show(); // to hell with all of it
            }
        });

        return iView;
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
    private void initView(View view){
        edtCustomPageLength = view.findViewById(R.id.edtCustomPageLength);
        edtCustomPageWidth = view.findViewById(R.id.edtCustomPageWidth);
        edtCustomPageHeight = view.findViewById(R.id.edtCustomPageHeight);
        swCustomPageAdvSp = view.findViewById(R.id.swCustomPageAdvSp);
        swCustomPageFan = view.findViewById(R.id.swCustomPageFan);
        btnCustomPageAdd = view.findViewById(R.id.btnCustomPageAdd);
//        fABtnOpenCart = view.findViewById(R.id.fABtnOpenCart);
    }

}
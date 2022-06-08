package com.example.noiseboxentree.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noiseboxentree.R;


public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
        super(R.layout.fragment_about);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iView = inflater.inflate(R.layout.fragment_about, container, false);



        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
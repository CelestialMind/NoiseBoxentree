package com.example.noiseboxentree;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.noiseboxentree.fragments.AboutFragment;
import com.example.noiseboxentree.fragments.CatalogFragment;
import com.example.noiseboxentree.fragments.CustomFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new CustomFragment();
            case 2:
                return new AboutFragment();
            default:
                return new CatalogFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

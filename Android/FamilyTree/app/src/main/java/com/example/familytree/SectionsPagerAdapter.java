package com.example.familytree;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

class SectionsPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public SectionsPagerAdapter(FragmentActivity fa) {
        super(fa);
    }


    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return arrayList.get(position);
//    }

    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}

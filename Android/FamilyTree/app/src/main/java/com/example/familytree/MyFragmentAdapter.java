package com.example.familytree;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

class MyFragmentAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> arrayList = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();

    public MyFragmentAdapter(FragmentActivity fa) {

        super(fa);
        Log.d("[ Fragment ]", "test 5");

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("[ Fragment ]", "test 4");
        return arrayList.get(position);

    }


    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
        Log.d("[ Fragment ]", "test 6");
    }
    @Override
    public int getItemCount() {
        Log.d("[ Fragment ]", "test 7");
        return arrayList.size();
    }
    //to setup title of the tab layout
    @Nullable

    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}

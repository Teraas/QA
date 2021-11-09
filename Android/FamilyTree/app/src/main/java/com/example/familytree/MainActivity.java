package com.example.familytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout mTabLayout;
    private ViewPager2 mainPager;
    private FirebaseAuth mAuth;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FamilyTree");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Tabs
        mSectionsPagerAdapter = new SectionsPagerAdapter(this  );
        mainPager = (ViewPager2) findViewById(R.id.main_viewPager);
        // add Fragments in your ViewPagerFragmentAdapter class
        mSectionsPagerAdapter.addFragment(new HomeFragment());
        mSectionsPagerAdapter.addFragment(new ChatFragment());
        mSectionsPagerAdapter.addFragment(new FamilyFragment());


        mainPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mainPager.setAdapter(mSectionsPagerAdapter);
        ArrayList<String> tabNames = new ArrayList<>();
        tabNames.add("Home");
        tabNames.add("Chats");
        tabNames.add("Family");

        //Migrated to viewPager2
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        new TabLayoutMediator(mTabLayout, mainPager,
                (tab, position) -> tab.setText(tabNames.get(position))
        ).attach();
        //mTabLayout.setupWithViewPager();
    }
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_Logout) {
            FirebaseAuth.getInstance().signOut();
            sendToStartPage();
        }
        if(item.getItemId() == R.id.main_Profile) {
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            sendToStartPage();
        }
    }

    private void sendToStartPage() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

}
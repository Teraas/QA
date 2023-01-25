package com.example.familytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout mTabLayout;
    private ViewPager2 mainViewPager;
    private FirebaseAuth mAuth;
    private MyFragmentAdapter mMyFragmentAdapter;
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    SharedPreferences sharedpreferences;
    public static final String mypreference = "famlyPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Famlyy");
        // Initialize
        mAuth = FirebaseAuth.getInstance();

        mTabLayout = findViewById(R.id.main_tabs);
        // Tabs
        mMyFragmentAdapter = new MyFragmentAdapter(this  );
        mainViewPager = findViewById(R.id.main_viewPager);


        // add Fragments in your ViewPagerFragmentAdapter class
        mMyFragmentAdapter.addFragment(new HomeFragment());
        //mMyFragmentAdapter.addFragment(new ChatFragment());
        mMyFragmentAdapter.addFragment(new FamilyFragment(this));
        mMyFragmentAdapter.addFragment(new NotificationFragment());
        mainViewPager.setAdapter(mMyFragmentAdapter);


        //mainViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mainViewPager.setUserInputEnabled(false);

        ArrayList<String> tabNames = new ArrayList<>();
        tabNames.add("Home");
        //tabNames.add("Chats");
        tabNames.add("Family");
        tabNames.add("Notifications");
        Log.d("[ Fragment ]", "test 1");

        new TabLayoutMediator(mTabLayout, mainViewPager,
                (tab, position) -> tab.setText(tabNames.get(position))
        ).attach();
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mainViewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(TabLayout.Tab tab, int position) {
//                // position of the current tab and that tab
//                tab.setText(tabNames.get(position));
//            }
//        });
//        tabLayoutMediator.attach();
        //mTabLayout.setupWithViewPager();
        //mainPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //mainViewPager.setCurrentItem(0, false);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("[ Fragment ]", "test 2");
                mainViewPager.setCurrentItem(tab.getPosition());
                tab.view.getTab().getText();
                if (tab.getPosition() == 1){
                    //set graph
                    //Fragment f = mMyFragmentAdapter.createFragment(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.d("[ Fragment ]", "test 3");
                //mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
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
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("token","default");
        if(token.equals("default") ) {
            sendToStartPage();
        }
    }

    private void sendToStartPage() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    private void processImage(){

    }

}
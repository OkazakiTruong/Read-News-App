package com.example.appdocbao;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nav,navTop;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("WELCOME");


        navTop = findViewById(R.id.bnav_top);
        nav = findViewById(R.id.bnav_Home);
        nav.setSelectedItemId(R.id.nav_home);
        viewPager2 = findViewById(R.id.viewPager2);

        setViewPager();



        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        break;
                    case R.id.nav_categlories:
                        startActivity(new Intent(MainActivity.this,Categlories.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_other:
                        startActivity(new Intent(MainActivity.this,Other.class));
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
        ;
        navTop.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navTinTuc:
                        item.setChecked(true);
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.navMobile:
                        item.setChecked(true);
                        viewPager2.setCurrentItem(1);
                        viewPager2.cancelDragAndDrop();
                        break;
                    case R.id.navGocNhin:
                        item.setChecked(true);
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.navTipAndTricks:
                        item.setChecked(true);
                        viewPager2.setCurrentItem(3);
                        break;

                }
                return false;
            }
        });
    }

    private void setViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                switch (position){
                    case 0:
                        navTop.getMenu().findItem(R.id.navTinTuc).setChecked(true);
                        break;
                    case 1:
                        navTop.getMenu().findItem(R.id.navMobile).setChecked(true);
                        break;
                    case 2:
                        navTop.getMenu().findItem(R.id.navGocNhin).setChecked(true);
                        break;
                    case 3:
                        navTop.getMenu().findItem(R.id.navTipAndTricks).setChecked(true);
                        break;
                }

            }
        });
    }


}
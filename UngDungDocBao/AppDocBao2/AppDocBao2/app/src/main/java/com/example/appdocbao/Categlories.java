package com.example.appdocbao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Categlories extends AppCompatActivity {
    ImageView ivVatVo, ivGenk,ivTeachRoom,ivVfo,ivTechZ,ivTechMac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categlories);

        getView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CATEGLORIES");

        BottomNavigationView nav = findViewById(R.id.bnav_Categlories);
        nav.setSelectedItemId(R.id.nav_categlories);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(Categlories.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_categlories:
                        break;
                    case R.id.nav_other:
                        startActivity(new Intent(Categlories.this,Other.class));
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

        // su kien chon bao
        chonBao();
    }

    private void chonBao() {
        ivVatVo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoVatVo.class));
            }
        });
        ivGenk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoGenK.class));
            }
        });
        ivVfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoVfo.class));
            }
        });
        ivTeachRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoTeachRoom.class));
            }
        });
        ivTechZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoTechZ.class));
            }
        });
        ivTechMac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Categlories.this,BaoTechMag.class));
            }
        });
    }

    private void getView() {
        ivGenk = findViewById(R.id.ivGenk);
        ivTeachRoom = findViewById(R.id.ivTeachRoom);
        ivVfo = findViewById(R.id.ivVfo);
        ivVatVo = findViewById(R.id.ivVatVo);
        ivTechZ = findViewById(R.id.ivTeachZ);
        ivTechMac = findViewById(R.id.ivTechMac);
    }
}
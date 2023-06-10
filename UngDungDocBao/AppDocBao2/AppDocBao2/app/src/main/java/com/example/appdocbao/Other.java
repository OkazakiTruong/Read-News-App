package com.example.appdocbao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Other extends AppCompatActivity {
    TextView tvAboutUs, tvShareApp,tvCloseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        getData();
        //lua chon
        chosse();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OTHER");

        BottomNavigationView nav = findViewById(R.id.bnav_Other);
        nav.setSelectedItemId(R.id.nav_other);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(Other.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_categlories:
                        startActivity(new Intent(Other.this,Categlories.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_other:
                        break;
                }
                return false;
            }
        });

    }

    private void chosse() {
        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Other.this,aboutUS.class));
            }
        });
        tvCloseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Other.this);
                builder.setTitle("EXIT").setMessage("Do you want to close this app?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();

            }
        });
        tvShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body="Dowmload now";
                String Sub="https://drive.google.com/drive/folders/1JTEeqppldJro61gQYQnxud8fparxtTv1?usp=sharing";
                intent.putExtra(Intent.EXTRA_TEXT,Body);
                intent.putExtra(Intent.EXTRA_TEXT,Sub);
            Intent shareIntent = Intent.createChooser(intent,null);
            startActivity(shareIntent);

            }
        });
    }

    private void getData() {
        tvAboutUs = findViewById(R.id.tvAboutUs);
        tvShareApp = findViewById(R.id.tvShareApp);
        tvCloseApp = findViewById(R.id.tvCloseApp);
    }
}
package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaoTechMagChitiet extends AppCompatActivity {
    WebView wvChitiet;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bao TechMag");

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkChiTiet");

        wvChitiet = findViewById(R.id.wvChiTiet);
        wvChitiet.loadUrl(link);
        wvChitiet.setWebViewClient(new WebViewClient());
    }
}

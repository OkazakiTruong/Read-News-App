package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class BaoVfoChiTiet extends AppCompatActivity {
    WebView wvChiTiet;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        wvChiTiet = findViewById(R.id.wvChiTiet);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkVfoChiTiet");

        wvChiTiet.getSettings().setJavaScriptEnabled(true);
        wvChiTiet.loadUrl(link);

    }
    }

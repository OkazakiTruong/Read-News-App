package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaoTeachRoomChitiet extends AppCompatActivity {
    WebView wvChiTiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bao TechRom");

        wvChiTiet = findViewById(R.id.wvChiTiet);
        Intent intent = getIntent();
        String link = intent.getStringExtra("techrumChitiet");

        wvChiTiet.loadUrl(link);
        wvChiTiet.setWebViewClient(new WebViewClient());
    }
}

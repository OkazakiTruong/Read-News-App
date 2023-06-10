package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class TipChiTiet extends AppCompatActivity {
    WebView wvChiTiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        wvChiTiet = findViewById(R.id.wvChiTiet);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTipChiTiet");

        wvChiTiet.loadUrl(link);
    }
}

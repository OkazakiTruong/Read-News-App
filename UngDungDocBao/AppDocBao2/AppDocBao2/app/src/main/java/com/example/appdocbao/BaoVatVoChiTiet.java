package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;

public class BaoVatVoChiTiet extends AppCompatActivity {
    WebView wvChiTiet;
    FloatingActionButton btnSpeak,btnStopSpeak;
    TextToSpeech textToSpeech;
    String noiDungBao = "nothing";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bao Vat Vo");

        wvChiTiet =findViewById(R.id.wvChiTiet);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnStopSpeak = findViewById(R.id.btnStopSpeak);

        Intent intent = getIntent();
        String link = intent.getStringExtra("fileVatVo");

        wvChiTiet.getSettings().setJavaScriptEnabled(true);
        wvChiTiet.loadUrl(link);
        wvChiTiet.setWebViewClient(new WebViewClient());

        new getSpeakStr().execute(link);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
            if(i == TextToSpeech.SUCCESS){
                textToSpeech.setLanguage(Locale.forLanguageTag("vi-VN"));
            }
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaoVatVoChiTiet.this, "Đọc Báo", Toast.LENGTH_SHORT).show();
                textToSpeech.speak(noiDungBao,TextToSpeech.QUEUE_FLUSH,null);
                btnSpeak.setVisibility(View.INVISIBLE);
                btnStopSpeak.setVisibility(View.VISIBLE);
            }
        });
        btnStopSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaoVatVoChiTiet.this, "Ngừng đọc báo", Toast.LENGTH_SHORT).show();
                textToSpeech.stop();
                btnSpeak.setVisibility(View.VISIBLE);
                btnStopSpeak.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.shutdown();
    }

    class getSpeakStr extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            ;
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                noiDungBao = doc.getElementsByClass("container container--narrow").text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return noiDungBao;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }



}

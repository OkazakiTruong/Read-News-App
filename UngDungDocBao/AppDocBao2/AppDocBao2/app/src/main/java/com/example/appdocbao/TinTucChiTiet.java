package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

public class TinTucChiTiet extends AppCompatActivity {
    WebView wvChiTiet;
    String noiDungDoc;
    TextToSpeech textToSpeech;
    FloatingActionButton btnSpeak, btnStopSpeak;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        wvChiTiet = findViewById(R.id.wvChiTiet);

        Intent intent = getIntent();
        String link = intent.getStringExtra("tinTucChiTiet");

        btnSpeak = findViewById(R.id.btnSpeak);
        btnStopSpeak = findViewById(R.id.btnStopSpeak);

        wvChiTiet.getSettings().setJavaScriptEnabled(true);
        wvChiTiet.loadUrl(link);
        wvChiTiet.setWebViewClient(new WebViewClient());

         new getSpeakString().execute(link);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
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
                textToSpeech.speak(noiDungDoc,TextToSpeech.QUEUE_FLUSH,null);
                btnSpeak.setVisibility(View.INVISIBLE);
                btnStopSpeak.setVisibility(View.VISIBLE);
            }
        });
        btnStopSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.stop();
                btnStopSpeak.setVisibility(View.INVISIBLE);
                btnSpeak.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.shutdown();
    }

    class getSpeakString extends AsyncTask<String, Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                noiDungDoc  = doc.getElementsByClass("container container--narrow").text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return noiDungDoc;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

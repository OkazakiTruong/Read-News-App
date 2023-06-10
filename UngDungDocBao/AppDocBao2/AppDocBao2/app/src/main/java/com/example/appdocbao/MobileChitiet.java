package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;

public class MobileChitiet extends AppCompatActivity {
    WebView wvChiTiet;
    TextToSpeech textToSpeech;
    FloatingActionButton btnSpeak, btnStopSpeak;
    String noiDungDoc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_chitiet);

        wvChiTiet = findViewById(R.id.wvChiTiet);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnStopSpeak = findViewById(R.id.btnStopSpeak);



        Intent intent = getIntent();
        String link = intent.getStringExtra("MoblieChiTiet");

        wvChiTiet.loadUrl(link);
        wvChiTiet.setWebViewClient(new WebViewClient());
        wvChiTiet.getSettings().setJavaScriptEnabled(true);

        new getSpeakStr().execute(link);

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

    class getSpeakStr extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements e = doc.getElementsByTag("p");
                for(Element ele: e){
                    builder.append(ele.text());
                }
                noiDungDoc = builder.toString();
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

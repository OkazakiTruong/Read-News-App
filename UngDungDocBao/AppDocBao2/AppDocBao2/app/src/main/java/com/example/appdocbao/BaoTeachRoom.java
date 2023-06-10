package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BaoTeachRoom extends AppCompatActivity {
    ListView lvTitle;
    ArrayList<DocBao> arrDocBao;
    DocBaoAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_title_bao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bao TechRom");

        lvTitle = findViewById(R.id.lvTitle);
        arrDocBao = new ArrayList<>();
        adapter = new DocBaoAdapter(this,R.layout.title_line,arrDocBao);
        lvTitle.setAdapter(adapter);

        new getData().execute("https://www.techrum.vn/");

        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BaoTeachRoom.this,BaoTeachRoomChitiet.class);
                intent.putExtra("techrumChitiet",arrDocBao.get(i).getLink());
                startActivity(intent);
            }
        });

    }
    class getData extends AsyncTask<String,Integer, ArrayList<DocBao>>{

        @Override
        protected ArrayList<DocBao> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements e =doc.getElementsByClass("porta-article-item");
                for(int i=0; i<e.size();i++){
                    if(e.get(i).getAllElements().hasClass(("adsbygoogle"))){
                    }else {
                        String title = e.get(i).getElementsByClass("block-header").text();
                        String link = "https://techrum.vn" + e.get(i).getElementsByTag("a").attr("href");
                        String img = e.get(i).getElementsByTag("img").attr("src");
                        arrDocBao.add(new DocBao(img,title,link));

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return arrDocBao;
        }

        @Override
        protected void onPostExecute(ArrayList<DocBao> docBaos) {
            super.onPostExecute(docBaos);
            adapter.notifyDataSetInvalidated();
        }
    }
}

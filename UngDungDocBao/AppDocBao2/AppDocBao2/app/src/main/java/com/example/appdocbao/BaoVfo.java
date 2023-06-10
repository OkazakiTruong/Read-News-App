package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BaoVfo extends AppCompatActivity {
    ListView lvTitle;
    ArrayList<DocBao> arrDocBao;
    DocBaoAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_title_bao);
        getView();
        new getData().execute("https://vfo.vn/");
        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(BaoVfo.this,BaoVfoChiTiet.class);
                intent.putExtra("linkVfoChiTiet",arrDocBao.get(i).getLink());
                startActivity(intent);
            }
        });
    }

    private void getView() {
        lvTitle = findViewById(R.id.lvTitle);
        arrDocBao = new ArrayList<>();
        adapter = new DocBaoAdapter(this,R.layout.title_line,arrDocBao);
        lvTitle.setAdapter(adapter);
    }
    class getData extends AsyncTask<String, Integer,ArrayList>{

        @Override
        protected ArrayList doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements e = doc.select("li[class = block-row porta-article-item]");
                for(int i=0; i<e.size();i++){
                    String img = e.get(i).getElementsByTag("img").attr("src");
                    String title = e.get(i).getElementsByTag("h3").text();
                    String link = "https://vfo.vn/"+ e.get(i).getElementsByTag("a").attr("href");
                    arrDocBao.add(new DocBao(img,title,link));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrDocBao;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            adapter.notifyDataSetInvalidated();
        }
    }
}

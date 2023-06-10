package com.example.appdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TipFragMent extends Fragment {
    ListView lvTitle;
    ArrayList<DocBao> arrDocBao;
    DocBaoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danh_sach_title_bao,container,false);
        lvTitle = view.findViewById(R.id.lvTitle);
        arrDocBao = new ArrayList<>();
        adapter = new DocBaoAdapter(getActivity(),R.layout.title_line,arrDocBao);
        lvTitle.setAdapter(adapter);
        new getData().execute("https://vatvostudio.vn/category/tips-and-tricks/");
        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),TipChiTiet.class);
                intent.putExtra("linkTipChiTiet",arrDocBao.get(i).getLink());
                startActivity(intent);
            }
        });
        return view;
    }
    class getData extends AsyncTask<String,Integer, ArrayList<DocBao>>{

        @Override
        protected ArrayList<DocBao> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements e = doc.getElementsByClass("list-item");
                for(Element ele:e){
                    String title = ele.getElementsByTag("h3").text();
                    String img = ele.getElementsByTag("img").attr("data-src");
                    String link = ele.getElementsByTag("a").attr("href");
                    arrDocBao.add(new DocBao(img,title,link));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<DocBao> docBaos) {
            super.onPostExecute(docBaos);
            adapter.notifyDataSetInvalidated();
        }
    }
}

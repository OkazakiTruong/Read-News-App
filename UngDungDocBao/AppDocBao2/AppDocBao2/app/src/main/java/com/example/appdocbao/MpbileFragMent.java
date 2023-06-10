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

public class MpbileFragMent extends Fragment {
    ArrayList<DocBao> arrDocBao;
    DocBaoAdapter adapter;
    ListView lvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danh_sach_title_bao,container,false);
        lvTitle = view.findViewById(R.id.lvTitle);
        arrDocBao = new ArrayList<>();
        adapter = new DocBaoAdapter(getActivity(),R.layout.title_line,arrDocBao);
        lvTitle.setAdapter(adapter);
        new getData().execute("https://genk.vn/mobile.chn");
        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),MobileChitiet.class);
                intent.putExtra("MoblieChiTiet",arrDocBao.get(i).getLink());
                startActivity(intent);
            }
        });
        return view;
    }
    class getData extends AsyncTask<String,Integer,ArrayList<DocBao>>{

        @Override
        protected ArrayList<DocBao> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                doc.getElementsByClass("shownews gknews_box").remove();
                Elements e = doc.select("li[data-marked-zoneid = genk_home_bs7]");
                for(Element ele : e){
                    String title = ele.getElementsByTag("h4").text();
                    String link ="https://genk.vn"+ele.getElementsByTag("a").attr("href");
                    String img = ele.getElementsByTag("img").attr("src");
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

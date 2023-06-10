package com.example.appdocbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DocBaoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<DocBao> arrDocbao;

    public DocBaoAdapter(Context context, int layout, ArrayList<DocBao> arrDocbao) {
        this.context = context;
        this.layout = layout;
        this.arrDocbao = arrDocbao;
    }

    @Override
    public int getCount() {
        return arrDocbao.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        //anh xa
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        ImageView ivImgTitle = view.findViewById(R.id.ivImgTitle);

        //gan gia tri
        DocBao docBao = arrDocbao.get(i);
        tvTitle.setText(docBao.getTitle());
        Picasso.with(context).load(docBao.getImgTitle()).into(ivImgTitle);

        return view;
    }
}

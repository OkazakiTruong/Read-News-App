package com.example.appdocbao;

import java.io.Serializable;

public class DocBao implements Serializable {
    private String imgTitle;
    private String title;
    private String link;

    public DocBao(String imgTitle, String title, String link) {
        this.imgTitle = imgTitle;
        this.title = title;
        this.link = link;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

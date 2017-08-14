package com.example.aki.jpat;

import java.io.Serializable;

/**
 * Created by Aki on 8/13/2017.
 */

public class Data implements Serializable {

    private String author;
    private String title;
    private String desc;
    private String time;
    private String image;
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Data(String author, String title, String desc, String time, String image, String url) {
        this.author=author;
        this.title=title;
        this.desc=desc;
        this.time=time;
        this.image=image;
        this.url=url;


    }

}

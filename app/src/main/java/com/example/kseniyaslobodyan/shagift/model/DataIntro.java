package com.example.kseniyaslobodyan.shagift.model;

import java.io.Serializable;

public class DataIntro implements Serializable{
    String text;
    Integer img;

    public DataIntro(String text, Integer img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public Integer getImg() {
        return img;
    }
}
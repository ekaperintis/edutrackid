package com.ekaperintis.guru.Model;

public class Information {
    private int id;
    private String title;
    private String shortdesc;
    private String image;


    public Information(int id, String title, String shortdesc, String image) {

        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getImage() {
        return image;
    }


}

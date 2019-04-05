package com.ekaperintis.guru.Model;

public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private double price;
    private int image;
    private String image_url;

    //public Product(int id, String title, String shortdesc, double rating, double price, int image) {
    public Product(int id, String title, String shortdesc, double rating, double price, String image_url) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        //this.image = image;
        this.image_url = image_url;
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

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getImage_url() {
        return image_url;
    }
}
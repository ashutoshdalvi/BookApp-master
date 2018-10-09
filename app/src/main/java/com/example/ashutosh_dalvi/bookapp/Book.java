package com.example.ashutosh_dalvi.bookapp;

public class Book {

    private String name;
    private String description;
    private String imgurl;
    private String bookurl;

    public Book() {
    }

    public Book(String name, String description, String imgurl, String bookurl) {
        this.name = name;
        this.description = description;
        this.imgurl = imgurl;
        this.bookurl = bookurl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getBookurl() {
        return bookurl;
    }
}

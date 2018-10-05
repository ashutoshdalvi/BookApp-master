package com.example.ashutosh_dalvi.bookapp;

public class Book {

    private String name;
    private String category;
    private String description;
    private int thumbnail;

    public Book() {
    }

    public Book(String name, String category, String description, int thumbnail) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

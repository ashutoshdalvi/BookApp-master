package com.example.ashutosh_dalvi.bookapp;

public class Sample {
    private String name,description,book_url,image_url;

    public Sample() {
    }

    public Sample(String name, String description, String book_url, String image_url) {
        this.name = name;
        this.description = description;
        this.book_url = book_url;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

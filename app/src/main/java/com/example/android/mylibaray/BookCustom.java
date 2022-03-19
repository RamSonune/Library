package com.example.android.mylibaray;

public class BookCustom {
    String bookNameCustom ;
    String authorCustom ;
    int imageResCustom;
    String urlCustom;
    String description;

    public BookCustom(String bookNameCustom ,String authorCustom,int imageResCustom,String urlCustom){
        this.bookNameCustom = bookNameCustom ;
        this.authorCustom = authorCustom;
        this.imageResCustom = imageResCustom;
        this.urlCustom = urlCustom;

    }

    public BookCustom(String bookNameCustom ,String authorCustom,int imageResCustom){
        this.bookNameCustom = bookNameCustom ;
        this.authorCustom = authorCustom;
        this.imageResCustom = imageResCustom;


    }

    public BookCustom(String bookNameCustom ,String authorCustom,int imageResCustom,String urlCustom,String description){
        this.bookNameCustom = bookNameCustom ;
        this.authorCustom = authorCustom;
        this.imageResCustom = imageResCustom;
        this.urlCustom = urlCustom;
        this.description = description;

    }

    public String getBookNameCustom() {
        return bookNameCustom;
    }

    public String getAuthorCustom() {
        return authorCustom;
    }

    public int getImageResCustom() {
        return imageResCustom;
    }
}

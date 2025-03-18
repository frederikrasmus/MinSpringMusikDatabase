package com.example.minspringmusikdatabase.Model;

public class Album {

    private int album_id;
    private String name;
    private int genre_id;
    private int company_id;

    public Album() {
    }

    public Album(int album_id, String name, int genre_id, int company_id) {
        this.album_id = album_id;
        this.name = name;
        this.genre_id = genre_id;
        this.company_id = company_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public String getName() {
        return name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setAlbum_id(int id) {
        this.album_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}

package com.example.minspringmusikdatabase.Model;

public class Album {

    private int album_id;
    private String name;
    private int genre_id;
    private int company_id;
    private String genre_name;
    private String company_name;
    private Artist artists;

    public Album() {
    }

    public Album(int album_id, String name, int genre_id, int company_id) {
        this.album_id = album_id;
        this.name = name;
        this.genre_id = genre_id;
        this.company_id = company_id;
    }

    public Album(int album_id, String name, int genre_id, int company_id, String genre_name, String company_name) {
        this.album_id = album_id;
        this.name = name;
        this.genre_id = genre_id;
        this.company_id = company_id;
        this.genre_name = genre_name;
        this.company_name = company_name;
    }

    public Album(int album_id, String name, int genre_id, int company_id, String genre_name,
                 String company_name, Artist artists) {
        this.album_id = album_id;
        this.name = name;
        this.genre_id = genre_id;
        this.company_id = company_id;
        this.genre_name = genre_name;
        this.company_name = company_name;
        this.artists = artists;
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

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Artist getArtists() {
        return artists;
    }

    public void setArtists(Artist artists) {
        this.artists = artists;
    }
}

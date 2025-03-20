package com.example.minspringmusikdatabase.Model;

public class Artist {

    private int artist_id;
    private String name;
    private String country;
    private String gender;
    private int age;

    public Artist() {
    }

    public Artist(int artist_id, String name, String country, String gender, int age) {
        this.artist_id = artist_id;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.age = age;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

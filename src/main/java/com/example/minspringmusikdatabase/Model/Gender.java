package com.example.minspringmusikdatabase.Model;

public class Gender {

    private int gender_id;
    private String name;

    public Gender() {
    }

    public Gender(int gender_id, String name) {
        this.gender_id = gender_id;
        this.name = name;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

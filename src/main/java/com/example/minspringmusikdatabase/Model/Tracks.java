package com.example.minspringmusikdatabase.Model;

public class Tracks {

    private int Track_id;
    private int album_id;
    private String title;
    private String duration;

    public Tracks() {
    }

    public Tracks(int track_id, int album_id, String title, String duration) {
        Track_id = track_id;
        this.album_id = album_id;
        this.title = title;
        this.duration = duration;
    }

    public int getTrack_id() {
        return Track_id;
    }

    public void setTrack_id(int track_id) {
        Track_id = track_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

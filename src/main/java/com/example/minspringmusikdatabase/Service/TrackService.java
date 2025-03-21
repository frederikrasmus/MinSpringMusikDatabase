package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Genre;
import com.example.minspringmusikdatabase.Model.Tracks;
import com.example.minspringmusikdatabase.Repository.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepo trackRepository; // Instans af AlbumRepo klassen.


    public List<Tracks> fetchAll() {
        return trackRepository.fetchAll(); // Forretningslogik
    }

    public void addTracks(Tracks t) {
        trackRepository.addTrack(t); // Forretningslogik
    }

    public Tracks findTrackById(int id) {
        return trackRepository.findTrackById(id);
    }

    public boolean deleteTrack(int id) {
        return trackRepository.deleteTrack(id);
    }

    public void updateTrack(Tracks t) {
        trackRepository.updateTrack(t);
    }
}

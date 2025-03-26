package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Artist;
import com.example.minspringmusikdatabase.Repository.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepo artistRepo;

    public List<Artist> fetchAll() {
        return artistRepo.fetchAll();
    }

    public void addArtist(Artist a) {
        artistRepo.addArtist(a);
    }

    public Artist FindArtistById(int id) {
        return artistRepo.findArtistById(id);
    }

    public boolean deleteArtist(int id) {
        return artistRepo.deleteArtist(id);
    }

    public void updateArtist(Artist a) {
        artistRepo.updateArtist(a);
    }
}

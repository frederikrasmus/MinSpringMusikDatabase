package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private final AlbumRepo albumRepository; // Instans af AlbumRepo klassen.

    public AlbumService(AlbumRepo albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> fetchAllWithDetails() {
        return albumRepository.fetchAllWithDetails(); // Forretningslogik
    }

    public void addAlbum(Album album) {
        albumRepository.addAlbum(album); // Forretningslogik
    }

    public Album findAlbumById(int id) {
        return albumRepository.findAlbumById(id);
    }

    public boolean deleteAlbum(int id) {
        return albumRepository.deleteAlbum(id);
    }

    public void updateAlbum(Album a) {
        albumRepository.updateAlbum(a);
    }
}

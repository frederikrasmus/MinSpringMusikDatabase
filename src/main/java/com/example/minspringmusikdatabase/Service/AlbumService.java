package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Repository.AlbumRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepo albumRepository; // Instans af AlbumRepo klassen.

    public AlbumService(AlbumRepo albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> fetchAll() {
        return albumRepository.fetchAll(); // Forretningslogik
    }

    public void addAlbum(Album album) {
        albumRepository.addAlbum(album); // Forretningslogik
    }
}

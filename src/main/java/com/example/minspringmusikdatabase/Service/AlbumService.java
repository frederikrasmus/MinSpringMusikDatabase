package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Artist;
import com.example.minspringmusikdatabase.Model.Tracks;
import com.example.minspringmusikdatabase.Repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Definerer denne klasse som en service klasse som håndterer forretningslogik
public class AlbumService {

    @Autowired //Spring injicering af Album repository.
    private AlbumRepo albumRepository;
    // Instans af AlbumRepo klassen. Så service kan interagere med database klassen

    public List<Album> fetchAllWithDetails() {
        return albumRepository.fetchAllWithDetails(); // Forretningslogik
    }

    public int addAlbum(Album album) {
        return albumRepository.addAlbum(album); // Forretningslogik
    }

    public Album findAlbumById(int id) {
        return albumRepository.findAlbumById(id);
    }

    public void deleteAlbum(int id) {
        albumRepository.deleteAlbum(id);
    }

    public void updateAlbum(Album a) {
        albumRepository.updateAlbum(a);
    }

    public List<Tracks> fetchTracksByAlbumId(int albumId) {
        return albumRepository.fetchTracksByAlbumId(albumId);
    }

    public Artist fetchArtistByAlbumID(int albumId) {
        return albumRepository.fetchArtistByAlbumID(albumId);
    }

    public void addArtistToAlbum(int artistId, int albumId) {
        albumRepository.addArtistToAlbum(artistId, albumId);
    }
}

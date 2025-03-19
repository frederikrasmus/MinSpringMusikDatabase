package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Genre;
import com.example.minspringmusikdatabase.Repository.AlbumRepo;
import com.example.minspringmusikdatabase.Repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private final GenreRepo genreRepository; // Instans af AlbumRepo klassen.

    public GenreService(GenreRepo genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> fetchAll() {
        return genreRepository.fetchAll(); // Forretningslogik
    }

    public void addGenre(Genre g) {
       genreRepository.addGenre(g); // Forretningslogik
    }

    public Genre findGenreById(int id) {
        return genreRepository.findGenreById(id);
    }

    public boolean deleteGenre(int id) {
        return genreRepository.deleteGenre(id);
    }

    public void updateGenre(Genre g) {
        genreRepository.updateGenre(g);
    }
}

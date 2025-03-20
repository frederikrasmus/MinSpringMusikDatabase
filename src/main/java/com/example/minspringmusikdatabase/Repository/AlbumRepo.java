package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Artist;
import com.example.minspringmusikdatabase.Model.Tracks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Angiver at denne klasse er en repository-komponent i spring, så vi kan bruge dependency injection
public class AlbumRepo {

    @Autowired // Spring injicerer automatisk en JdbcTemplate instans
    private JdbcTemplate jdbcTemplate;

    // Henter alle albums med detaljer fra databasen
    public List<Album> fetchAllWithDetails() {
        String sql = "SELECT a.album_id, a.name, a.genre_id, a.company_id, g.name " +
                "AS genre_name, r.name AS company_name FROM album a " +
                "JOIN genre g ON a.genre_id = g.genre_id " +
                "JOIN recordcompany r ON a.company_id = r.company_id " +
                "ORDER BY a.album_id ASC;";
        // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Album> rowMapper = new BeanPropertyRowMapper<>(Album.class);
        //Vi smider data ind i en liste som vi så kan køre gennem et loop
        List<Album> albums = jdbcTemplate.query(sql, rowMapper);

        // For-each loop som tildeler et album en artist ud fra album_id.
        for(Album album : albums) {
            Artist artists = fetchArtistByAlbumID(album.getAlbum_id());
            album.setArtists(artists);
        }
        // Returnerer listen albums med artist indkluderet.
        return albums;
    }

    // Tilføjer et nyt album i databasen
    public void addAlbum(Album a) {
        String sql = "INSERT INTO album (name, genre_id, company_id) VALUES (?, ?, ?)"; // PreparedStatement
        // Bruger jdbcTemplate til at indsætte data i databasen.
        jdbcTemplate.update(sql, a.getName(), a.getGenre_id(), a.getCompany_id());
    }

    // Finder et album baseret på id.
    public Album findAlbumById(int id) {
        String sql ="SELECT a.*, g.name AS genre_name FROM album a " +
                "JOIN genre g ON a.genre_id = g.genre_id WHERE album_id = ?";
        // Mapper query resultatet til et album album-objekt.
        RowMapper<Album> rowMapper = new BeanPropertyRowMapper<>(Album.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Sletter et album baseret på id
    public boolean deleteAlbum(int id) {
        String sql = "DELETE FROM album WHERE album_id =?";
        //Hvis update returnerer et tal større end 0, er sletningen lykkedes
        // Dette tal den returnerer, er en repræsentation af antallet af rækker der er blevet sletter.
        return jdbcTemplate.update(sql, id) > 0;
    }

    // Opdaterer et albums oplysninger i databasen
    public void updateAlbum(Album a) {
        String sql = "UPDATE album SET name = ?, genre_id = ?, company_id = ? WHERE album_id = ?";
        // Opdaterer i databasen med nye værdier.
        jdbcTemplate.update(sql, a.getName(), a.getGenre_id(), a.getCompany_id(), a.getAlbum_id());
    }

    public List<Tracks> fetchTracksByAlbumId(int albumId) {
        String sql = "SELECT * FROM tracks WHERE album_id = ?";
        RowMapper<Tracks> rowMapper = new BeanPropertyRowMapper<>(Tracks.class);
        return jdbcTemplate.query(sql, rowMapper, albumId);
    }

    public Artist fetchArtistByAlbumID(int albumId) {
        String sql = "SELECT a.* FROM artist a JOIN artist_album aa " +
                "ON a.artist_id = aa.artist_id WHERE aa.album_id = ? LIMIT 1";
        RowMapper<Artist> rowMapper = new BeanPropertyRowMapper<>(Artist.class);
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, albumId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

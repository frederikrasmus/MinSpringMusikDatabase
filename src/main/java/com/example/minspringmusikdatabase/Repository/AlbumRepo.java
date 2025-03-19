package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Album;
import org.springframework.beans.factory.annotation.Autowired;
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
                "JOIN recordcompany r ON a.company_id = r.company_id;";
        // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Album> rowMapper = new BeanPropertyRowMapper<>(Album.class);
        return jdbcTemplate.query(sql, rowMapper);
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

}

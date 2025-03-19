package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepo {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AlbumRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public void addAlbum(Album a) {
        String sql = "INSERT INTO album (name, genre_id, company_id) VALUES (?, ?, ?)"; // PreparedStatement
        jdbcTemplate.update(sql, a.getName(), a.getGenre_id(), a.getCompany_id());
    }

    public Album findAlbumById(int id) {
        String sql ="SELECT a.*, g.name AS genre_name FROM album a JOIN genre g ON a.genre_id = g.genre_id WHERE album_id = ?";
        RowMapper<Album> rowMapper = new BeanPropertyRowMapper<>(Album.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public boolean deleteAlbum(int id) {
        String sql = "DELETE FROM album WHERE album_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public void updateAlbum(Album a) {
        String sql = "UPDATE album SET name = ?, genre_id = ?, company_id = ? WHERE album_id = ?";
        jdbcTemplate.update(sql, a.getName(), a.getGenre_id(), a.getCompany_id(), a.getAlbum_id());
    }

}

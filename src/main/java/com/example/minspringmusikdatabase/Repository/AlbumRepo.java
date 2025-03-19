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

    public List<Album> fetchAll() {
        String sql = "SELECT * FROM album"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Album> rowMapper = new BeanPropertyRowMapper<>(Album.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addAlbum(Album a) {
        String sql = "INSERT INTO album (name, genre_id, company_id) VALUES (?, ?, ?)"; // PreparedStatement
        jdbcTemplate.update(sql, a.getName(), a.getGenre_id(), a.getCompany_id());
    }

    public Album findAlbum(int id) {
        return null;
    }

    public boolean deleteAlbum(int id) {
        return false;
    }

    public void updateAlbum(Album a) {

    }

}

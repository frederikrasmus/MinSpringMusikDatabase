package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Artist> fetchAll() {
        String sql = "SELECT * FROM artist"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Artist> rowMapper = new BeanPropertyRowMapper<>(Artist.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Tilføjer genre i databasen
    public void addArtist(Artist a) {
        String sql = "INSERT INTO artist (name,country,gender,age) VALUES (?,?,?,?)"; // PreparedStatement
        jdbcTemplate.update(sql, a.getName(), a.getCountry(),a.getGender(),a.getAge());
    }

    // Finder genre baseret på genre id
    public Artist findArtistById(int id) {
        String sql ="SELECT * FROM artist WHERE artist_id = ?";
        RowMapper<Artist> rowMapper = new BeanPropertyRowMapper<>(Artist.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Slette genre baseret på genre id
    public boolean deleteArtist(int id) {
        String sql = "DELETE FROM artist WHERE artist_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    // Opdaterer genre baseret på genre id
    public void updateArtist(Artist a) {
        String sql = "UPDATE artist SET name = ?, country = ?, gender = ?, age = ? WHERE artist_id = ?";
        jdbcTemplate.update(sql, a.getName(), a.getCountry(), a.getGender(), a.getAge());
    }
}

package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Tracks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Tracks> fetchAll() {
        String sql = "SELECT * FROM tracks"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i track-klassen.
        RowMapper<Tracks> rowMapper = new BeanPropertyRowMapper<>(Tracks.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Tilføjer tracks i databasen
    public void addTrack(Tracks t) {
        String sql = "INSERT INTO tracks (album_id,title,duration) VALUES (?, ?, ?)"; // PreparedStatement
        jdbcTemplate.update(sql, t.getAlbum_id(),t.getTitle(), t.getDuration().toString());
    }

    // Finder tracks baseret på track id
    public Tracks findTrackById(int id) {
        String sql ="SELECT * FROM tracks WHERE track_id = ?";
        RowMapper<Tracks> rowMapper = new BeanPropertyRowMapper<>(Tracks.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Slette tracks baseret på track id
    public boolean deleteTrack(int id) {
        String sql = "DELETE FROM tracks WHERE track_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    // Opdaterer tracks baseret på track id
    public void updateTrack(Tracks t) {
        String sql = "UPDATE tracks SET album_id = ?, title = ?, duration = ? WHERE track_id = (?, ?, ?)";
        jdbcTemplate.update(sql, t.getAlbum_id(), t.getTitle(), t.getDuration());
    }

}
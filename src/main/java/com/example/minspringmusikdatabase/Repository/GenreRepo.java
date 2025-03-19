package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Genre> fetchAll() {
        String sql = "SELECT * FROM genre"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Genre> rowMapper = new BeanPropertyRowMapper<>(Genre.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addGenre(Genre g) {
        String sql = "INSERT INTO genre (name) VALUES (?)"; // PreparedStatement
        jdbcTemplate.update(sql, g.getName());
    }

    public Genre findGenreById(int id) {
        String sql ="SELECT * FROM genre WHERE genre_id = ?";
        RowMapper<Genre> rowMapper = new BeanPropertyRowMapper<>(Genre.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public boolean deleteGenre(int id) {
        String sql = "DELETE FROM genre WHERE genre_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public void updateGenre(Genre g) {
        String sql = "UPDATE genre SET name = ? WHERE genre_id = ?";
        jdbcTemplate.update(sql, g.getName(), g.getGenre_id());
    }

}

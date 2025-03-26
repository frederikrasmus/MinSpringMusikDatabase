package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Artist;
import com.example.minspringmusikdatabase.Model.Country;
import com.example.minspringmusikdatabase.Model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Gender> fetchAll() {
        String sql = "SELECT * FROM gender"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Gender> rowMapper = new BeanPropertyRowMapper<>(Gender.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addGender(Gender g) {
        String sql = "INSERT INTO gender (name) VALUES (?)"; // PreparedStatement
        jdbcTemplate.update(sql, g.getName());
    }

    public Gender findGenderById(int id) {
        String sql ="SELECT * FROM gender WHERE gender_id = ?";
        RowMapper<Gender> rowMapper = new BeanPropertyRowMapper<>(Gender.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public boolean deleteGender(int id) {
        String sql = "DELETE FROM gender WHERE gender_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public void updateGender(Gender g) {
        String sql = "UPDATE gender SET name = ? WHERE gender_id = ?";
        jdbcTemplate.update(sql, g.getName());
    }
}

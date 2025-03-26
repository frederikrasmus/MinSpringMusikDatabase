package com.example.minspringmusikdatabase.Repository;
import com.example.minspringmusikdatabase.Model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Country> fetchAll() {
        String sql = "SELECT * FROM country"; // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<Country> rowMapper = new BeanPropertyRowMapper<>(Country.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Tilføjer genre i databasen
    public void addCountry(Country c) {
        String sql = "INSERT INTO country (name) VALUES (?)"; // PreparedStatement
        jdbcTemplate.update(sql, c.getName());
    }

    // Finder genre baseret på genre id
    public Country findCountryById(int id) {
        String sql ="SELECT * FROM country WHERE country_id = ?";
        RowMapper<Country> rowMapper = new BeanPropertyRowMapper<>(Country.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Slette genre baseret på genre id
    public boolean deleteCountry(int id) {
        String sql = "DELETE FROM country WHERE country_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    // Opdaterer genre baseret på genre id
    public void updateCountry(Country c) {
        String sql = "UPDATE country SET name  WHERE country_id = ?";
        jdbcTemplate.update(sql, c.getName());
    }
}

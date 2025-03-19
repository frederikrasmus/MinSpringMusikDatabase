package com.example.minspringmusikdatabase.Repository;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Genre;
import com.example.minspringmusikdatabase.Model.RecordCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordCompanyRepo {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RecordCompanyRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RecordCompany> fetchAll() {
        String sql = "SELECT * FROM recordcompany";
        // Vores query vi bruger til at hente data med.
        // Vi mapper automatisk kolonner fra databasen til felter i album-klassen.
        RowMapper<RecordCompany> rowMapper = new BeanPropertyRowMapper<>(RecordCompany.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addRecordCompany(RecordCompany r) {
        String sql = "INSERT INTO recordcompany (name, address, phone_number) VALUES (?, ?, ?)"; // PreparedStatement
        jdbcTemplate.update(sql, r.getName(), r.getAddress(), r.getPhone_number());
    }

    public RecordCompany findRecordCompanyById(int id) {
        String sql ="SELECT * FROM recordcompany WHERE company_id = ?";
        RowMapper<RecordCompany> rowMapper = new BeanPropertyRowMapper<>(RecordCompany.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public boolean deleteRecordCompany(int id) {
        String sql = "DELETE FROM recordcompany WHERE genre_id =?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public void updateRecordCompany(RecordCompany r) {
        String sql = "UPDATE recordcompany SET name = ?, address = ?, phone_number = ? WHERE genre_id = ?";
        jdbcTemplate.update(sql, r.getName(), r.getAddress(), r.getPhone_number(), r.getCompany_id());
    }

}

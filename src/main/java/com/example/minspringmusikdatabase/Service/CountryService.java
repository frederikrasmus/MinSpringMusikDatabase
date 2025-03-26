package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Artist;
import com.example.minspringmusikdatabase.Model.Country;
import com.example.minspringmusikdatabase.Repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepo countryRepo;

    public List<Country> fetchAll() {
        return countryRepo.fetchAll();
    }

    public void addCountry(Country c) {
        countryRepo.addCountry(c);
    }

    public Country FindCountryById(int id) {
        return countryRepo.findCountryById(id);
    }

    public boolean deleteCountry(int id) {
        return countryRepo.deleteCountry(id);
    }

    public void updateCountry(Country c) {
        countryRepo.updateCountry(c);
    }
}

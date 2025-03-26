package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.Country;
import com.example.minspringmusikdatabase.Model.Gender;
import com.example.minspringmusikdatabase.Repository.GenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    @Autowired
    private GenderRepo genderRepo;

    public List<Gender> fetchAll() {
        return genderRepo.fetchAll();
    }

    public void addGender(Gender g) {
        genderRepo.addGender(g);
    }

    public Gender FindGenderById(int id) {
        return genderRepo.findGenderById(id);
    }

    public boolean deleteGender(int id) {
        return genderRepo.deleteGender(id);
    }

    public void updateGender(Gender g) {
        genderRepo.updateGender(g);
    }
}

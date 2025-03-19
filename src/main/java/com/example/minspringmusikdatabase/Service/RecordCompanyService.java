package com.example.minspringmusikdatabase.Service;

import com.example.minspringmusikdatabase.Model.RecordCompany;
import com.example.minspringmusikdatabase.Repository.RecordCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordCompanyService {

    @Autowired
    private final RecordCompanyRepo recordCompanyRepo; // Instans af AlbumRepo klassen.


    public RecordCompanyService(RecordCompanyRepo recordCompanyRepo) {
        this.recordCompanyRepo = recordCompanyRepo;
    }

    public List<RecordCompany> fetchAll() {
        return recordCompanyRepo.fetchAll(); // Forretningslogik
    }

    public void addRecordCompany(RecordCompany r) {
        recordCompanyRepo.addRecordCompany(r); // Forretningslogik
    }

    public RecordCompany findRecordCompanyById(int id) {
        return recordCompanyRepo.findRecordCompanyById(id);
    }

    public boolean deleteRecordCompany(int id) {
        return recordCompanyRepo.deleteRecordCompany(id);
    }

    public void updateRecordCompany(RecordCompany r) {
        recordCompanyRepo.updateRecordCompany(r);
    }
}
package com.example.minspringmusikdatabase.Model;

public class RecordCompany {

    private int company_id;
    private String name;
    private String address;
    private String phone_number;

    public RecordCompany() {
    }

    public RecordCompany(int company_id, String name, String address, String phone_number) {
        this.company_id = company_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}

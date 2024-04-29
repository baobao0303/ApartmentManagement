package com.luaga.apartmentmanagement;

import java.util.ArrayList;
import java.util.List;

public class HelperClass {
    private String username, email, phone, password;
    private List<InforApartment> apartmentList;

    public HelperClass() {
        apartmentList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<InforApartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<InforApartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public void addApartment(InforApartment apartment) {
        apartmentList.add(apartment);
    }

    public void removeApartment(InforApartment apartment) {
        apartmentList.remove(apartment);
    }
}

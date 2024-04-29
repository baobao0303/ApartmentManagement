package com.luaga.apartmentmanagement;

public class InforApartment {
    private String renter = "";
    private String phone = "";
    private String contact = "";
    private String email = "";
    private double price = 0.0;
    private double priceWater = 0.0;
    private double priceElectricity = 0.0;
    private double priceGarbage = 0.0;
    private boolean laundry = false;
    private boolean roomGym = false;
    private boolean swimmingService = false;
    // Default constructor
    public InforApartment() {
        // Initialize default values or leave them empty
    }

    // Parameterized constructor
    public InforApartment(String renter, String phone, String contact, String email, double price, double priceWater, double priceElectricity, double priceGarbage, boolean laundry, boolean roomGym, boolean swimmingService) {
        this.renter = renter;
        this.phone = phone;
        this.contact = contact;
        this.email = email;
        this.price = price;
        this.priceWater = priceWater;
        this.priceElectricity = priceElectricity;
        this.priceGarbage = priceGarbage;
        this.laundry = laundry;
        this.roomGym = roomGym;
        this.swimmingService = swimmingService;
    }
    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWater() {
        return priceWater;
    }

    public void setPriceWater(double priceWater) {
        this.priceWater = priceWater;
    }

    public double getPriceElectricity() {
        return priceElectricity;
    }

    public void setPriceElectricity(double priceElectricity) {
        this.priceElectricity = priceElectricity;
    }

    public double getPriceGarbage() {
        return priceGarbage;
    }

    public void setPriceGarbage(double priceGarbage) {
        this.priceGarbage = priceGarbage;
    }

    public boolean isLaundry() {
        return laundry;
    }

    public void setLaundry(boolean laundry) {
        this.laundry = laundry;
    }

    public boolean isRoomGym() {
        return roomGym;
    }

    public void setRoomGym(boolean roomGym) {
        this.roomGym = roomGym;
    }

    public boolean isSwimmingService() {
        return swimmingService;
    }

    public void setSwimmingService(boolean swimmingService) {
        this.swimmingService = swimmingService;
    }
}

package com.example.carrentalapp.Models;

public class NewCarDatabase {
    String name;
    String address;
    String phone_number;
    String car_number;

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

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public NewCarDatabase(String name, String address, String phone_number, String car_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.car_number = car_number;
    }
}

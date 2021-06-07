package com.example.carrentalapp.Models;

public class BookingDatabase {
    String name;
    String address;
    String phone_number;
    String number_of_days;

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

    public String getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(String number_of_days) {
        this.number_of_days = number_of_days;
    }

    public BookingDatabase(String name, String address, String phone_number, String number_of_days) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.number_of_days = number_of_days;
    }
}

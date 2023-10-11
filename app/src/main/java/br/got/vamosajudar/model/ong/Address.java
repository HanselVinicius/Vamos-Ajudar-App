package br.got.vamosajudar.model.ong;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address(String street, String number, String city, String state, String postalCode, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", cidade='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

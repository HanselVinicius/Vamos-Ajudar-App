package br.got.vamosajudar.model.ong;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String number;
    private String cidade;
    private String state;
    private String postalCode;
    private String country;


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", cidade='" + cidade + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

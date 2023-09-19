package br.got.vamosajudar.model.ong;

import java.io.Serializable;

public class Contact implements Serializable {
    private String email;
    private String phone;
    private String website;


    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}

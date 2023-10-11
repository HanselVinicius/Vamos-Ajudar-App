package br.got.vamosajudar.model.ong;

import java.io.Serializable;

public class Contact implements Serializable {
    private String email;
    private String phone;
    private String website;


    public Contact(String email, String phone, String website) {
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}

package br.got.vamosajudar.model.ong;

public class Contact {
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

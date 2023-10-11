package br.got.vamosajudar.model.ong;

import java.io.Serializable;

public class OngRegisterDTO implements Serializable {
    private String name;
    private String description;
    private String goal;
    private Address address;
    private Contact contact;
    private String image;

    private String pix;

    public OngRegisterDTO(String name, String description, String goal, Address address, Contact contact, String image, String pix) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.address = address;
        this.contact = contact;
        this.image = image;
        this.pix = pix;
    }

    @Override
    public String toString() {
        return "OngRegisterDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", image='" + image + '\'' +
                ", pix='" + pix + '\'' +
                '}';
    }
}

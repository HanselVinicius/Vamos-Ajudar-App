package br.got.vamosajudar.model.ong;

import java.io.Serializable;
import java.util.Objects;

public class Ong implements Serializable {
    private String id;
    private String name;
    private String description;
    private String goal;
    private Address address;
    private Contact contact;
    private Boolean verified;
    private Boolean active;

    private String image;

    private String chavePix;

    private final static String LINK_API_IMAGE = "https://vamos-ajudar-api.henriquebarucco.com.br/v1/image/";


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    /**
     * mudança pedida pelo henrique barucco no dia  09/10 para salvar imagem de cada ong
     */
    public String getImageLink(){
        return LINK_API_IMAGE +this.getImage();
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getGoal() {
        return goal;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public Boolean getActive() {
        return active;
    }

    public String getChavePix() {
        return chavePix;
    }

    @Override
    public String toString() {
        return "Ong{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", verified=" + verified +
                ", active=" + active +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ong ong = (Ong) o;
        return Objects.equals(id, ong.id) && Objects.equals(name, ong.name) && Objects.equals(description, ong.description) && Objects.equals(goal, ong.goal) && Objects.equals(address, ong.address) && Objects.equals(contact, ong.contact) && Objects.equals(verified, ong.verified) && Objects.equals(active, ong.active) && Objects.equals(image, ong.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, goal, address, contact, verified, active, image);
    }
}

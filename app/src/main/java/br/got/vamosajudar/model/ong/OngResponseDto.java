package br.got.vamosajudar.model.ong;

import static br.got.vamosajudar.model.ong.Ong.LINK_API_IMAGE;

import java.io.Serializable;

public class OngResponseDto implements Serializable{

    private String id;
    private String name;
    private String description;
    private String goal;
    private Address address;
    private Contact contact;
    private String image;
    private String chavePix;


    public OngResponseDto(OngRegisterDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.goal = dto.getGoal();
        this.address = dto.getAddress();
        this.contact = dto.getContact();
        this.image = dto.getImage();
        this.chavePix = dto.getPix();

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getImage() {
        return image;
    }

    public String getChavePix() {
        return chavePix;
    }

    public String getImageLink(){
        return LINK_API_IMAGE +this.getImage();
    }

    @Override
    public String toString() {
        return "OngResponseDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", image='" + image + '\'' +
                ", chavePix='" + chavePix + '\'' +
                '}';
    }
}

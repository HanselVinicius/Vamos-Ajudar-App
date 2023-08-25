package br.got.vamosajudar.model.ong;

public class Ong {
    private String id;
    private String name;
    private String description;
    private String goal;
    private Address address;
    private Contact contact;
    private Boolean verified;
    private Boolean active;


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
                '}';
    }
}

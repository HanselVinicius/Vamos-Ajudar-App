package br.got.vamosajudar.model.user.dto;

public class UserRegisterDTO {

    private String login;
    private String email;
    private String password;
    private String name;

    private String image;


    public UserRegisterDTO(String login, String email, String password, String name, String image) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

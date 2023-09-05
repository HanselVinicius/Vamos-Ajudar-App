package br.got.vamosajudar.model.user.dto;

public class UserRegisterDTO {

    private String login;
    private String email;
    private String password;
    private String name;


    public UserRegisterDTO(String login, String email, String password, String name) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
    }



}

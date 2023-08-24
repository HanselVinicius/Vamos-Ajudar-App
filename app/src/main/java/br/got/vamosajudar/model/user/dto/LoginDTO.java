package br.got.vamosajudar.model.user.dto;

public class LoginDTO {

    private String login;
    private String password;

    public LoginDTO(String username, String password) {
        this.login = username;
        this.password = password;
    }
}

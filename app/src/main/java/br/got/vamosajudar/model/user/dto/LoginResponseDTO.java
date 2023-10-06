package br.got.vamosajudar.model.user.dto;

import java.io.Serializable;

import br.got.vamosajudar.model.user.UserRole;

public class LoginResponseDTO implements Serializable {

    private String email;
    private String login;
    private UserRole userRole;
    private String token;


    public LoginResponseDTO(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }


    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getUserRole() {
        return userRole;
    }
    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", userRole=" + userRole +
                ", token='" + token + '\'' +
                '}';
    }
}

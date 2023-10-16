package br.got.vamosajudar.model.user.dto;

import static br.got.vamosajudar.model.ong.Ong.LINK_API_IMAGE;

import java.io.Serializable;

import br.got.vamosajudar.model.ong.OngResponseDto;
import br.got.vamosajudar.model.user.UserRole;

public class LoginResponseDTO implements Serializable {

    private String name;
    private String email;
    private String login;
    private UserRole userRole;
    private String token;
    private String image;

    private OngResponseDto ong;


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

    public String getImage() {
        return LINK_API_IMAGE+image;
    }

    public OngResponseDto getOng() {
        return ong;
    }

    public void setOng(OngResponseDto ong) {
        this.ong = ong;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", userRole=" + userRole +
                ", token='" + token + '\'' +
                ", image='" + image + '\'' +
                ", ong=" + ong +
                '}';
    }
}

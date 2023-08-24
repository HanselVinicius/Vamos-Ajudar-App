package br.got.vamosajudar.model.user.dto;

public class LoginResponseDTO {

    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}

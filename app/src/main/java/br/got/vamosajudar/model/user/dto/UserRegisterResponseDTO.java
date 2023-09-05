package br.got.vamosajudar.model.user.dto;

public class UserRegisterResponseDTO {

    private String name;
    private String email;
    private String login;
    private String userRole;


    public UserRegisterResponseDTO(String name, String email, String login, String userRole) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.userRole = userRole;
    }
}

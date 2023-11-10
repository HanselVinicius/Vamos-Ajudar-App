package br.got.vamosajudar.model.user;

public  class User {

    private Long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private UserRole userRole;
    private Boolean active;


    public User(Long id, String login, String password, String email, String name, UserRole userRole, Boolean active) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.userRole = userRole;
        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Boolean getActive() {
        return active;
    }
}

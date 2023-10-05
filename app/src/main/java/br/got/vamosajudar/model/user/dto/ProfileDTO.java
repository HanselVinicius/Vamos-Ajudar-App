package br.got.vamosajudar.model.user.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    private String name;


    @Override
    public String toString() {
        return "ProfileDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

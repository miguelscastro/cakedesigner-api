package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String name;
    private String email;
    private String password;
}

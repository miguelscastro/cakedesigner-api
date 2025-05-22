package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDTO {
    private String name;
    private String email;
    private String password;
}

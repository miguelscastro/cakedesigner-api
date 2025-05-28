package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private String name;
    private String email;
    private String password;
}

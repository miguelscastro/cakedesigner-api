package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRequestDTO {
    private String email;
    private String password;
}

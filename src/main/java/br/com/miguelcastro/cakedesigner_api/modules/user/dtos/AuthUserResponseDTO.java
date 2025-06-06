package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponseDTO {
    private TokenDTO access_token;
    private ViewUserResponseDTO user;
}

package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRequestDTO {
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula")
    @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula")
    @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um número")
    @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "A senha deve conter pelo menos um caractere especial")
    private String password;
}

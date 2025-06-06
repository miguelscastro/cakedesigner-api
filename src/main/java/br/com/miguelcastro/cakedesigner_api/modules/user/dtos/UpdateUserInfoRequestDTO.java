package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserInfoRequestDTO(
                @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name,

                @Email(message = "Invalid email") String email,

                @Size(min = 8, max = 100, message = "Password must have at least 8 characters") @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter") @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter") @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number") @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "Password must contain at least one special character") String password) {

}

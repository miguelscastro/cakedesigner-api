package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserResponseDTO {
    private String name;
    private String email;
    private String profileImage;
    private UUID id;
    private String role;
}

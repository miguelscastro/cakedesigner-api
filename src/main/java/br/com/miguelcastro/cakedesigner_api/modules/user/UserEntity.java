package br.com.miguelcastro.cakedesigner_api.modules.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import br.com.miguelcastro.cakedesigner_api.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_user", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nm_user", nullable = false)
    private String name;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Length(min = 10, max = 100)
    @Column(name = "ds_password", nullable = false)
    private String password;

    @Column(name = "ds_profile_image_path")
    private String profileImage;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "ds_role", nullable = false)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    @Column(name = "dt_creation", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime updatedAt;
}

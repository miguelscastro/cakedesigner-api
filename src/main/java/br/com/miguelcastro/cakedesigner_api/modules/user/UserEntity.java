package br.com.miguelcastro.cakedesigner_api.modules.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "nm_user", nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    @Pattern(regexp = "^[\\w.-]+@(?:gmail\\.com|hotmail\\.com|outlook\\.com|yahoo\\.com\\.br|yahoo\\.com|icloud\\.com|live\\.com)$", message = "Email domain is not allowed")
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must have at least 8 characters")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
    @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "Password must contain at least one special character")
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

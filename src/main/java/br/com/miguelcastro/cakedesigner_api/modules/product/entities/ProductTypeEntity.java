package br.com.miguelcastro.cakedesigner_api.modules.product.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_types")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_product_type")
    private UUID id;

    @NotBlank(message = "Type name is required")
    @Size(max = 100, message = "Type name cannot exceed 100 characters")
    @Column(name = "nm_product_type", nullable = false, length = 100)
    private String name;

    @CreationTimestamp
    @Column(name = "dt_creation", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime updatedAt;
}

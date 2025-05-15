package br.com.miguelcastro.cakedesigner_api.modules.productType;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "nm_product_type")
    private String name;

    @CreationTimestamp
    @Column(name = "dt_creation")
    private LocalDateTime createdAt;

}

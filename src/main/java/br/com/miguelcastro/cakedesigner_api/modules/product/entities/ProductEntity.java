package br.com.miguelcastro.cakedesigner_api.modules.product.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_product")
    private UUID id;

    @Column(name = "nm_product")
    private String name;

    @Column(name = "ds_product")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "cd_product_type", insertable = false, updatable = false)
    private ProductTypeEntity productTypeEntity;

    @Column(name = "cd_product_type")
    private UUID productTypeId;

    @Column(name = "vl_price")
    private Double price;

    @Column(name = "ds_image_path")
    private String image;

    @CreationTimestamp
    @Column(name = "dt_creation")
    private LocalDateTime createdAt;

}

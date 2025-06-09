package br.com.miguelcastro.cakedesigner_api.modules.order.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_products")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_order_product")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "cd_order", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product", nullable = false)
    private ProductEntity product;

    @Column(name = "qt_product", nullable = false)
    private Integer quantity;

    @Column(name = "vl_product", nullable = false)
    private Double price;
}

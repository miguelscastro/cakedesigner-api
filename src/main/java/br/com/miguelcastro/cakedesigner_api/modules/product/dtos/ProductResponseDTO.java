package br.com.miguelcastro.cakedesigner_api.modules.product.dtos;

import java.util.List;
import java.util.UUID;

import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;

public record ProductResponseDTO(
        UUID id,
        String title,
        String description,
        List<String> tags,
        double price,
        String image) {
    public static ProductResponseDTO fromEntity(ProductEntity product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                List.of(product.getProductTypeEntity().getName().toUpperCase()),
                product.getPrice(),
                product.getImage());
    }
}

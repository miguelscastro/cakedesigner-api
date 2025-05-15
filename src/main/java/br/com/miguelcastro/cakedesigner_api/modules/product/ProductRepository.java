package br.com.miguelcastro.cakedesigner_api.modules.product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByName(String name);
}

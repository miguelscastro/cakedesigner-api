package br.com.miguelcastro.cakedesigner_api.modules.productType;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {
    Optional<ProductTypeEntity> findByName(String name);
}

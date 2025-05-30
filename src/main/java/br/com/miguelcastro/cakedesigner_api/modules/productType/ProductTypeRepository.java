package br.com.miguelcastro.cakedesigner_api.modules.productType;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {
    Optional<ProductTypeEntity> findByName(String name);
}

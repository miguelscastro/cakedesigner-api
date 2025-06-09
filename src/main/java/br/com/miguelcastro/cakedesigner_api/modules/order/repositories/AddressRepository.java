package br.com.miguelcastro.cakedesigner_api.modules.order.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
    Optional<AddressEntity> findByCepAndNumber(String cep, String number);
}

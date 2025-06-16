package br.com.miguelcastro.cakedesigner_api.modules.order.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findAllByUserId(UUID userId);

}

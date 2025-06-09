package br.com.miguelcastro.cakedesigner_api.modules.order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderProductEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, UUID> {

}

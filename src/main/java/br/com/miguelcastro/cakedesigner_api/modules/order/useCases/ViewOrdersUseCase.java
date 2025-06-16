package br.com.miguelcastro.cakedesigner_api.modules.order.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.repositories.OrderRepository;

@Service
public class ViewOrdersUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> execute(UUID userId) {
        return orderRepository.findAllByUserId(userId);

    }
}

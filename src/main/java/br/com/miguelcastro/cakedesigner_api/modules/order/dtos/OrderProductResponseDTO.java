package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderProductEntity;

public record OrderProductResponseDTO(UUID productId,
        String name,
        BigDecimal quantity,
        BigDecimal price) {
    public static OrderProductResponseDTO fromEntity(OrderProductEntity op) {
        return new OrderProductResponseDTO(
                op.getProduct().getId(),
                op.getProduct().getName(),
                op.getQuantity(),
                op.getPrice());
    }
}

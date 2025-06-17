package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.miguelcastro.cakedesigner_api.modules.order.entities.AddressEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewOrdersResponseDTO {
    private UUID orderId;
    private String userId;
    private BigDecimal deliveryFee;
    private AddressEntity address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    List<OrderProductResponseDTO> products;

    public static ViewOrdersResponseDTO fromEntity(OrderEntity entity) {
        return new ViewOrdersResponseDTO(
                entity.getId(),
                entity.getUser().getId().toString(),
                entity.getDeliveryFee(),
                entity.getAddress(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getOrderProducts().stream()
                        .map(OrderProductResponseDTO::fromEntity)
                        .toList());
    }

}

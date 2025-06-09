package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOrderRequestDTO(

        @NotNull(message = "Address must not be null") @Valid AddressDTO address,

        @NotNull(message = "Delivery fee must not be null") @Min(value = 0, message = "Delivery fee must be greater than or equal to 0") Double deliveryFee,

        @NotNull(message = "Products must not be null") @Size(min = 1, message = "At least one product must be included in the order") List<OrderProductDTO> orderedProducts) {

}

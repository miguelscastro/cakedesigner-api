package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderProductDTO(

                @NotNull(message = "Product ID must not be null") UUID productId,

                @NotNull(message = "Quantity must not be null") @Min(value = 1, message = "Quantity must be at least 1") Integer quantity,

                @NotNull(message = "Product price must not be null") @Min(value = 0, message = "Product price must be greater than or equal to 0") Double price) {
}
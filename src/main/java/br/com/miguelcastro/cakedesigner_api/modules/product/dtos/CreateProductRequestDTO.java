package br.com.miguelcastro.cakedesigner_api.modules.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateProductRequestDTO(

    @NotBlank(message = "Name is required") String name,

    @NotBlank(message = "Description is required") String description,

    @NotBlank(message = "The product must have a type") String productTypeName,

    @PositiveOrZero(message = "Price must be equal or higher than zero") Double price,

    String image

) {
}

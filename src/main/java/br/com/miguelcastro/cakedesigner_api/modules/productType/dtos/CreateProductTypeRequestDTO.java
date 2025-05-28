package br.com.miguelcastro.cakedesigner_api.modules.productType.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateProductTypeRequestDTO(
        @NotBlank(message = "Name is required") String name) {
}

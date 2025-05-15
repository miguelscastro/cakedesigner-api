package br.com.miguelcastro.cakedesigner_api.modules.product.dtos;

public record CreateProductRequestDTO(String name, String description, String productTypeName, double price,
        String image) {

}

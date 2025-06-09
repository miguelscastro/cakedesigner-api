package br.com.miguelcastro.cakedesigner_api.modules.product.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.CreateProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductRepository;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductTypeRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductEntity execute(CreateProductRequestDTO dto) {

        this.productRepository.findByName(dto.name()).ifPresent(p -> {
            throw new FoundException(("Product already exists"));
        });

        var productTypeInfo = this.productTypeRepository.findByName(dto.productTypeName())
                .orElseThrow(() -> {
                    throw new FoundException("Type is invalid");
                });

        ProductEntity product = ProductEntity.builder()
                .name(dto.name())
                .description(dto.description())
                .productTypeId(productTypeInfo.getId())
                .price(dto.price())
                .image(dto.image())
                .build();

        return this.productRepository.save(product);
    }
}

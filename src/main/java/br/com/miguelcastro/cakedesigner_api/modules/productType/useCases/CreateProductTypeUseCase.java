package br.com.miguelcastro.cakedesigner_api.modules.productType.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.modules.productType.ProductTypeEntity;
import br.com.miguelcastro.cakedesigner_api.modules.productType.ProductTypeRepository;
import br.com.miguelcastro.cakedesigner_api.modules.productType.dtos.CreateProductTypeRequestDTO;

@Service
public class CreateProductTypeUseCase {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductTypeEntity execute(CreateProductTypeRequestDTO productTypeRequestDTO) {
        this.productTypeRepository.findByName(productTypeRequestDTO.name()).ifPresent(productType -> {
            throw new FoundException("Type already exists");
        });

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setName(productTypeRequestDTO.name());

        return this.productTypeRepository.save(productTypeEntity);
    }
}
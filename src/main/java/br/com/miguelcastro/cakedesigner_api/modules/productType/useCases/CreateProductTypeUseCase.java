package br.com.miguelcastro.cakedesigner_api.modules.productType.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.modules.productType.ProductTypeEntity;
import br.com.miguelcastro.cakedesigner_api.modules.productType.ProductTypeRepository;

@Service
public class CreateProductTypeUseCase {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductTypeEntity execute(ProductTypeEntity productTypeEntity) {
        this.productTypeRepository.findByName(productTypeEntity.getName()).ifPresent(productType -> {
            throw new FoundException("Type already exists");
        });
        return this.productTypeRepository.save(productTypeEntity);
    }
}
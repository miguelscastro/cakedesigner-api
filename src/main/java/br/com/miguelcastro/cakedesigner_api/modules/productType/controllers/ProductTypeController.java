package br.com.miguelcastro.cakedesigner_api.modules.productType.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.productType.ProductTypeEntity;
import br.com.miguelcastro.cakedesigner_api.modules.productType.useCases.CreateProductTypeUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manage/product-types")
public class ProductTypeController {

    @Autowired
    CreateProductTypeUseCase createProductTypeUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProductTypeEntity productTypeEntity) {
        try {
            var result = this.createProductTypeUseCase.execute(productTypeEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

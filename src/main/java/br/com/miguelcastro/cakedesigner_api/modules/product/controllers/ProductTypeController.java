package br.com.miguelcastro.cakedesigner_api.modules.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.CreateProductTypeRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductTypeEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.useCases.CreateProductTypeUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.product.useCases.ViewAllProductTypesUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manage/product-types")
public class ProductTypeController {

    @Autowired
    private CreateProductTypeUseCase createProductTypeUseCase;

    @Autowired
    private ViewAllProductTypesUseCase viewAllProductTypesUseCase;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateProductTypeRequestDTO createProductTypeRequestDTO) {
        try {
            var result = this.createProductTypeUseCase.execute(createProductTypeRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductTypeEntity>> getAllProductTypes() {
        return ResponseEntity.ok(viewAllProductTypesUseCase.execute());
    }
}

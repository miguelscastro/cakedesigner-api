package br.com.miguelcastro.cakedesigner_api.modules.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.product.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.ProductRepository;
import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.CreateProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.ProductResponseDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.useCases.CreateProductUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manage/product")
public class ProductController {

    @Autowired
    CreateProductUseCase createProductUseCase;

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateProductRequestDTO createProductRequestDTO) {
        try {
            var result = this.createProductUseCase.execute(createProductRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> list() {
        List<ProductEntity> products = productRepository.findAll();

        List<ProductResponseDTO> response = products.stream()
                .map(ProductResponseDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }
}

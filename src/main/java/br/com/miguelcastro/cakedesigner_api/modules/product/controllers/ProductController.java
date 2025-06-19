package br.com.miguelcastro.cakedesigner_api.modules.product.controllers;

import br.com.miguelcastro.cakedesigner_api.exceptions.NotFoundException;
import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.CreateNewProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.ProductResponseDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.UpdateProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.miguelcastro.cakedesigner_api.modules.product.useCases.CreateProductUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.product.useCases.UpdateProductUseCase;

@RestController
@RequestMapping("/manage/product")
public class ProductController {

    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("productTypeId") UUID productTypeId,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            CreateNewProductRequestDTO dto = CreateNewProductRequestDTO.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .productTypeId(productTypeId.toString())
                    .image(image)
                    .build();

            var result = this.createProductUseCase.execute(dto);
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

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(@ModelAttribute UpdateProductRequestDTO dto) {
        try {
            var result = this.updateProductUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            var product = this.productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found"));

            this.productRepository.delete(product);

            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

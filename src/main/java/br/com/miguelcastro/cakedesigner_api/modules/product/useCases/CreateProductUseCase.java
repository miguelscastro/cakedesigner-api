package br.com.miguelcastro.cakedesigner_api.modules.product.useCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.CreateNewProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductRepository;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductTypeRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductEntity execute(CreateNewProductRequestDTO dto) {

        this.productRepository.findByName(dto.getName()).ifPresent(p -> {
            throw new FoundException("Product already exists");
        });

        var productType = this.productTypeRepository.findById(UUID.fromString(dto.getProductTypeId()))
                .orElseThrow(() -> new FoundException("Type is invalid"));

        String imagePath = null;
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            imagePath = saveImageAndGetPath(dto.getImage());
        }

        ProductEntity product = ProductEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .productTypeEntity(productType)
                .image(imagePath)
                .build();

        return this.productRepository.save(product);
    }

    private String saveImageAndGetPath(MultipartFile image) {
        String uploadDir = "uploads/images";

        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String originalFilename = image.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString() + extension;

        Path filePath = Paths.get(uploadDir, newFileName);

        try {
            Files.copy(image.getInputStream(), filePath);
            return uploadDir + "/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao salvar a imagem");
        }
    }
}

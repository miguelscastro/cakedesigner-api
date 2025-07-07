package br.com.miguelcastro.cakedesigner_api.modules.product.useCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.miguelcastro.cakedesigner_api.modules.product.dtos.UpdateProductRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductRepository;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductTypeRepository;

@Service
public class UpdateProductUseCase {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductTypeRepository productTypeRepository;

  public ProductEntity execute(UpdateProductRequestDTO dto) {
    var product = this.productRepository.findById(dto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + dto.getId()));

    var productType = this.productTypeRepository.findById(dto.getTypeId())
        .orElseThrow(() -> new IllegalArgumentException("Tipo de produto não encontrado com ID: " + dto.getTypeId()));

    if (dto.getImage() != null && !dto.getImage().isEmpty()) {
      String imageUrl = saveImageReplacingOld(dto.getImage(), product.getImage());
      product.setImage(imageUrl);
    }

    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setProductTypeEntity(productType);
    product.setPrice(dto.getPrice());

    return productRepository.save(product);
  }

  private String saveImageReplacingOld(MultipartFile image, String oldImageUrl) {
    String uploadDir = "uploads/images";

    File uploadPath = new File(uploadDir);
    if (!uploadPath.exists()) {
      uploadPath.mkdirs();
    }

    String filename;
    if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
      filename = Paths.get(oldImageUrl).getFileName().toString();
    } else {
      String originalFilename = image.getOriginalFilename();
      String extension = "";
      if (originalFilename != null && originalFilename.contains(".")) {
        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
      }
      filename = UUID.randomUUID().toString() + extension;
    }

    Path filePath = Paths.get(uploadDir, filename);

    try {
      Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
      String baseUrl = "https://cakedesigner.onrender.com";
      return baseUrl + "/" + uploadDir.replace("\\", "/") + "/" + filename;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Falha ao salvar a imagem");
    }
  }
}

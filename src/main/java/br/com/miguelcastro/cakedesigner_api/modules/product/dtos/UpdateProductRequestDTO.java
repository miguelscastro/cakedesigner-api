package br.com.miguelcastro.cakedesigner_api.modules.product.dtos;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequestDTO {
  private UUID id;
  private String name;
  private String description;
  private Double price;
  private UUID typeId;
  private MultipartFile image;
}

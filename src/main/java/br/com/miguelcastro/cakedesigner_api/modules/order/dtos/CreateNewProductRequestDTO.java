package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductRequestDTO {

  private String name;
  private String description;
  private String productTypeId;
  private MultipartFile image;

}

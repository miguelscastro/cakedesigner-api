package br.com.miguelcastro.cakedesigner_api.modules.product.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductTypeEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductTypeRepository;

@Service
public class ViewAllProductTypesUseCase {

  @Autowired
  private ProductTypeRepository productTypeRepository;

  public List<ProductTypeEntity> execute() {
    return productTypeRepository.findAll();
  }

}

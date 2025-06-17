package br.com.miguelcastro.cakedesigner_api.modules.order.useCases;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.NotFoundException;
import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.CreateOrderRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.order.entities.AddressEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.repositories.AddressRepository;
import br.com.miguelcastro.cakedesigner_api.modules.order.repositories.OrderRepository;
import br.com.miguelcastro.cakedesigner_api.modules.product.entities.ProductEntity;
import br.com.miguelcastro.cakedesigner_api.modules.product.repositories.ProductRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;

@Service
public class CreateOrderUseCase {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private AddressRepository addressRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProductRepository productRepository;

        public OrderEntity execute(UUID userId, CreateOrderRequestDTO dto) {
                UserEntity user = userRepository.findById(userId)
                                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

                AddressEntity address = this.addressRepository
                                .findByCepAndNumber(dto.address().cep(), dto.address().number())
                                .orElseGet(() -> {
                                        AddressEntity newAddress = AddressEntity.builder()
                                                        .cep(dto.address().cep())
                                                        .street(dto.address().street())
                                                        .number(dto.address().number())
                                                        .fullAddress(dto.address().fullAddress())
                                                        .neighborhood(dto.address().neighborhood())
                                                        .city(dto.address().city())
                                                        .state(dto.address().state())
                                                        .build();

                                        return this.addressRepository.save(newAddress);
                                });

                List<OrderProductEntity> orderedProducts = dto.orderedProducts().stream().map(dtoProduct -> {
                        ProductEntity product = productRepository.findById(dtoProduct.productId())
                                        .orElseThrow(() -> new NotFoundException(
                                                        "Product not found with id " + dtoProduct.productId()));

                        OrderProductEntity op = new OrderProductEntity();
                        op.setProduct(product);
                        op.setQuantity(dtoProduct.quantity());
                        op.setPrice(dtoProduct.price());
                        return op;

                }).collect(Collectors.toList());

                OrderEntity order = OrderEntity.builder()
                                .user(user)
                                .address(address)
                                .deliveryFee(dto.deliveryFee())
                                .orderProducts(orderedProducts)
                                .build();

                orderedProducts.forEach(op -> op.setOrder(order));

                return orderRepository.save(order);
        }
}

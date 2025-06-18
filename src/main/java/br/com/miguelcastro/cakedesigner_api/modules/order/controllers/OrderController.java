package br.com.miguelcastro.cakedesigner_api.modules.order.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.CreateOrderRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.ViewOrdersResponseDTO;
import br.com.miguelcastro.cakedesigner_api.modules.order.entities.OrderEntity;
import br.com.miguelcastro.cakedesigner_api.modules.order.useCases.CreateOrderUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.order.useCases.ViewAllOrdersUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.order.useCases.ViewOrdersUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Autowired
    private ViewOrdersUseCase viewOrdersUseCase;

    @Autowired
    private ViewAllOrdersUseCase viewAllOrdersUseCase;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user")
    public ResponseEntity<Object> create(HttpServletRequest request,
            @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        var userId = request.getAttribute("auth_id");

        try {
            var order = this.createOrderUseCase.execute(UUID.fromString(userId.toString()), createOrderRequestDTO);
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<Object> userOrders(HttpServletRequest request) {
        var userId = request.getAttribute("auth_id");

        try {
            List<OrderEntity> orders = this.viewOrdersUseCase.execute(UUID.fromString(userId.toString()));

            List<ViewOrdersResponseDTO> dtoList = orders.stream().map(ViewOrdersResponseDTO::fromEntity).toList();

            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Object> allOrders(HttpServletRequest request) {
        var userId = request.getAttribute("auth_id");

        try {
            List<OrderEntity> orders = this.viewAllOrdersUseCase.execute(UUID.fromString(userId.toString()));

            List<ViewOrdersResponseDTO> dtoList = orders.stream().map(ViewOrdersResponseDTO::fromEntity).toList();

            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

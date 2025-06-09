package br.com.miguelcastro.cakedesigner_api.modules.order.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.order.dtos.CreateOrderRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.order.useCases.CreateOrderUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user")
    public ResponseEntity<Object> create(HttpServletRequest request,
            @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        var userId = request.getAttribute("user_id");

        try {
            var orders = this.createOrderUseCase.execute(UUID.fromString(userId.toString()), createOrderRequestDTO);
            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

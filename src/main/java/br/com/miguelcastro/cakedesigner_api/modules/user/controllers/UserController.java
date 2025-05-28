package br.com.miguelcastro.cakedesigner_api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.ViewUserUseCase;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ViewUserUseCase viewUserUseCase;

    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/me")
    public ResponseEntity<Object> profile(HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        try {
            var profile = this.viewUserUseCase.execute(UUID.fromString(userId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

package br.com.miguelcastro.cakedesigner_api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.DeleteUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.UpdateUserInfoRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.DeleteUserUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.UpdateUserUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.ViewUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ViewUserUseCase viewUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Object> profile(HttpServletRequest request) {
        var userId = request.getAttribute("auth_id");

        try {
            var profile = this.viewUserUseCase.execute(UUID.fromString(userId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/profile")
    public ResponseEntity<Object> update(HttpServletRequest request,
            @Valid @RequestBody UpdateUserInfoRequestDTO updateUserInfoRequestDTO) {
        var userId = request.getAttribute("auth_id");

        try {
            var user = this.updateUserUseCase.execute(UUID.fromString(userId.toString()), updateUserInfoRequestDTO);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/profile/delete-account")
    public ResponseEntity<Object> user(HttpServletRequest request,
            @Valid @RequestBody DeleteUserRequestDTO deleteUserRequestDTO) {
        var userId = request.getAttribute("auth_id");

        try {
            var user = this.deleteUserUseCase.execute(UUID.fromString(userId.toString()), deleteUserRequestDTO);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

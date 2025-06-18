package br.com.miguelcastro.cakedesigner_api.modules.user.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.exceptions.UnauthorizedException;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.AuthUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.CreateUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.AuthUserUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.CreateUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO,
            HttpServletRequest request) {
        var authIdAttribute = request.getAttribute("auth_id");
        try {
            UUID authId = null;

            if (createUserRequestDTO.getRole() != null && createUserRequestDTO.getRole().name() == "ADMIN") {

                if (authIdAttribute == null) {
                    throw new UnauthorizedException("Only admins can create new admins");
                }

                authId = UUID.fromString(authIdAttribute.toString());
            }

            var result = this.createUserUseCase.execute(createUserRequestDTO, authId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> validate(@Valid @RequestBody AuthUserRequestDTO authUserRequestDTO) {
        try {
            var result = this.authUserUseCase.execute(authUserRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

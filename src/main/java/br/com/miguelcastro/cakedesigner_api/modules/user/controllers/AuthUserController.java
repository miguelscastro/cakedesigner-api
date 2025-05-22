package br.com.miguelcastro.cakedesigner_api.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.AuthUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.RegisterUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.AuthUserUseCase;
import br.com.miguelcastro.cakedesigner_api.modules.user.useCases.CreateUserUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> create(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            var result = this.createUserUseCase.execute(registerUserRequestDTO);
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

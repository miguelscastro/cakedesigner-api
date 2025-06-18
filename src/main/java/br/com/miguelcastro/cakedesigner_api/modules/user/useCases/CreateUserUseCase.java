package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.enums.UserRole;
import br.com.miguelcastro.cakedesigner_api.exceptions.ForbiddenException;
import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.exceptions.UnauthorizedException;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.CreateUserRequestDTO;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(CreateUserRequestDTO dto, UUID authId) {
        if (dto.getRole() != null && dto.getRole() == UserRole.ADMIN) {
            if (authId == null) {
                throw new UnauthorizedException("Only authenticated admins may create new ones");
            }

            var possibleAdmin = userRepository.findById(authId)
                    .orElseThrow(() -> new UnauthorizedException("Authenticated user not found."));

            if (possibleAdmin.getRole() != UserRole.ADMIN) {
                throw new ForbiddenException("Only admins can create new admins.");
            }
        }

        this.userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
            throw new FoundException("Failed to create new user");
        });
        var password = passwordEncoder.encode(dto.getPassword());

        UserEntity newUser = UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(password)
                .profileImage(dto.getProfileImage())
                .role(dto.getRole() != null ? dto.getRole() : UserRole.USER)
                .build();

        return this.userRepository.save(newUser);

    }
}

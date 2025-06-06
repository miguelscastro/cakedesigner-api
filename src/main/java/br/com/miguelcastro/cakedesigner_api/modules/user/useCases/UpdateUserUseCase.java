package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.NotFoundException;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.UpdateUserInfoRequestDTO;

@Service
public class UpdateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(UUID userId, UpdateUserInfoRequestDTO dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        if (dto.name() != null) {
            user.setName(dto.name());
        }
        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
        if (dto.password() != null) {
            var password = passwordEncoder.encode(dto.password());
            user.setPassword(password);
        }

        return userRepository.save(user);
    }
}

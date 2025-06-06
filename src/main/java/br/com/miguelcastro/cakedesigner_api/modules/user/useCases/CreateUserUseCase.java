package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.enums.UserRole;
import br.com.miguelcastro.cakedesigner_api.exceptions.FoundException;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.CreateUserRequestDTO;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(CreateUserRequestDTO dto) {
        this.userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
            throw new FoundException("Failed to create new user");
        });
        var password = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(password);

        UserEntity user = UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .profileImage(dto.getProfileImage())
                .role(dto.getRole() != null ? dto.getRole() : UserRole.USER)
                .build();

        return this.userRepository.save(user);

    }
}

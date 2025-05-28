package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.ViewUserResponseDTO;

@Service
public class ViewUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public ViewUserResponseDTO execute(UUID userId) {
        var user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var userDTO = ViewUserResponseDTO
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .id(user.getId())
                .build();

        return userDTO;

    }
}

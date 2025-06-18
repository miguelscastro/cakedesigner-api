package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.BadRequestException;
import br.com.miguelcastro.cakedesigner_api.exceptions.NotFoundException;
import br.com.miguelcastro.cakedesigner_api.exceptions.UnauthorizedException;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.DeleteUserRequestDTO;

@Service
public class DeleteUserUseCase {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserEntity execute(UUID userId, DeleteUserRequestDTO dto) {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

    if (!"confirmo a exclusão da minha conta".equalsIgnoreCase(dto.confirmation().trim())) {
      throw new BadRequestException("Frase de confirmação inválida.");
    }

    if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
      throw new UnauthorizedException("Senha incorreta.");
    }

    userRepository.delete(user);
    return user;
  }
}
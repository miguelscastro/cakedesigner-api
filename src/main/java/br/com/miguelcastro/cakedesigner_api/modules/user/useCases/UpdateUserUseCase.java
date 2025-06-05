package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miguelcastro.cakedesigner_api.exceptions.NotFoundException;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.UpdateUserInfoRequestDTO;

@Service
public class UpdateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UpdateUserInfoRequestDTO dto) {
        UserEntity user = userRepository.findById(dto.id())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + dto.id()));

        user.setName(dto.name());

        return userRepository.save(user);
    }
}

package br.com.miguelcastro.cakedesigner_api.modules.user.dtos;

import java.util.UUID;

public record UpdateUserInfoRequestDTO(String name, UUID id) {

}

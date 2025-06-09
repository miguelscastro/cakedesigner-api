package br.com.miguelcastro.cakedesigner_api.modules.order.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

                @NotBlank(message = "ZIP code must not be blank") @Pattern(regexp = "\\d{5}-\\d{3}", message = "ZIP code must be in the format 12345-678") String cep,

                @NotBlank(message = "Street must not be blank") String street,

                @NotBlank(message = "House number must not be blank") String number,

                String fullAddress,

                @NotBlank(message = "Neighborhood must not be blank") String neighborhood,

                @NotBlank(message = "City must not be blank") String city,

                @NotBlank(message = "State must not be blank") @Size(min = 2, max = 2, message = "State must be the 2-letter abbreviation") String state

) {
}
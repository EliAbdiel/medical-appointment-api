package xyz.eliabdiel.model.address.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterAddress(
        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String complement,
        @NotBlank String district,
        @NotBlank String city
) {
}

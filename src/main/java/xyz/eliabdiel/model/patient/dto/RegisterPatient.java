package xyz.eliabdiel.model.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import xyz.eliabdiel.model.address.dto.RegisterAddress;

public record RegisterPatient(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 10, max = 15) String telephone,
        @NotBlank String document,
        @NotNull @Valid RegisterAddress address
) {
}

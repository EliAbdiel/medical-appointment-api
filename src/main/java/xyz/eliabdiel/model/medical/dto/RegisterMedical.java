package xyz.eliabdiel.model.medical.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import xyz.eliabdiel.model.address.dto.RegisterAddress;
import xyz.eliabdiel.model.medical.Specialization;

public record RegisterMedical(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank
        String document,
        @NotNull
        Specialization specialization,
        @NotNull
        @Valid
        RegisterAddress address) {
}

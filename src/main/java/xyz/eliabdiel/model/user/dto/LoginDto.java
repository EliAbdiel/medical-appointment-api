package xyz.eliabdiel.model.user.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String usernameOrEmail,
        @NotBlank String password
) {
}

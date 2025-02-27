package xyz.eliabdiel.model.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password
) {
}

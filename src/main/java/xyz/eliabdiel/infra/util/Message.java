package xyz.eliabdiel.infra.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message {

    USERNAME_ALREADY_EXISTS("Username already exists"),
    USER_EMAIL_ALREADY_EXISTS("Email already exists"),
    ROLE_USER_NOT_FOUND_ERROR("Role user not found"),
    USER_REGISTER("User registered successfully!");

    @Getter
    private final String message;
}

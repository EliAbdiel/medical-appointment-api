package xyz.eliabdiel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eliabdiel.model.user.dto.LoginDto;
import xyz.eliabdiel.model.user.dto.RegisterDto;
import xyz.eliabdiel.service.user.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}

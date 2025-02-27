package xyz.eliabdiel.service.user;

import org.springframework.http.ResponseEntity;
import xyz.eliabdiel.model.user.dto.LoginDto;
import xyz.eliabdiel.model.user.dto.RegisterDto;

public interface UserService {

    ResponseEntity<?> register(RegisterDto registerDto);

    ResponseEntity<?> login(LoginDto loginDto);
}

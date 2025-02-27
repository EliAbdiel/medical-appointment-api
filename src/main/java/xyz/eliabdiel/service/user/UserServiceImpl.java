package xyz.eliabdiel.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eliabdiel.infra.exception.AppException;
import xyz.eliabdiel.infra.security.JwtTokenProvider;
import xyz.eliabdiel.infra.util.Message;
import xyz.eliabdiel.infra.util.UserRole;
import xyz.eliabdiel.model.user.UserEntity;
import xyz.eliabdiel.model.user.dto.JwtAuthResponse;
import xyz.eliabdiel.model.user.dto.LoginDto;
import xyz.eliabdiel.model.user.dto.RegisterDto;
import xyz.eliabdiel.model.user.role.Role;
import xyz.eliabdiel.repository.RoleRepository;
import xyz.eliabdiel.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        try {
            if (userRepository.existsByUsername(registerDto.username())) {
                throw new AppException(HttpStatus.BAD_REQUEST, Message.USERNAME_ALREADY_EXISTS.getMessage());
            }
            if (userRepository.existsByEmail(registerDto.email())) {
                throw new AppException(HttpStatus.BAD_REQUEST, Message.USER_EMAIL_ALREADY_EXISTS.getMessage());
            }
            var user = new UserEntity(registerDto);
            Set<Role> roles = new HashSet<>();
            var userRole = roleRepository.findByName(UserRole.ROLE_INDEX + UserRole.USER)
                    .orElseThrow(() -> new AppException(HttpStatus.INTERNAL_SERVER_ERROR, Message.ROLE_USER_NOT_FOUND_ERROR.getMessage()));
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);

            return ResponseEntity.ok(Message.USER_REGISTER.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.usernameOrEmail(),
                            loginDto.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            JwtAuthResponse authResponse = new JwtAuthResponse();
            authResponse.setAccessToken(token);

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
}

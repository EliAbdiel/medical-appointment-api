package xyz.eliabdiel.model.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.eliabdiel.model.user.dto.RegisterDto;
import xyz.eliabdiel.model.user.role.Role;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private Set<Role> roles;

    public UserEntity(RegisterDto register) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.active = true;
        this.name = register.name();
        this.username = register.username();
        this.email = register.email();
        this.password = passwordEncoder.encode(register.password());
    }

    public void deleteUser() {
        this.active = false;
    }
}

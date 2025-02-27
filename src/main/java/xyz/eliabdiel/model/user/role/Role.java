package xyz.eliabdiel.model.user.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.eliabdiel.model.user.UserEntity;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    private int id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}

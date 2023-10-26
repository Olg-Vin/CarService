package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.vinio.models.enums.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "roles")
public class UserRole extends BaseEntityId{

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}

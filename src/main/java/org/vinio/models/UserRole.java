package org.vinio.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.vinio.models.enums.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "role")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<User> users;
}

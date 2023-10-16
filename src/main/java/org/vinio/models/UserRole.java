package org.vinio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.vinio.models.enums.Role;

import java.util.UUID;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "role")
    private Role role;
}

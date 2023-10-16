package org.vinio.models;

import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "username", length = 255, nullable = false)
    private String username;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "role", nullable = false)
    private UserRole role;
    @Column(name = "image_url", nullable = false)
    private URL imageUrl;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;
}

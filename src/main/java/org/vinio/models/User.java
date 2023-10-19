package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "username", length = 255, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Column(name = "last_name", length = 255)
    private String lastName;
    @Column(name = "is_active")
    private boolean isActive;

    //    todo lazy

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "seller")
    private List<Offer> offers;
}

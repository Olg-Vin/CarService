package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Table(name = "users")
public class User extends BaseEntityCM{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private UserRole role;
    private String imageUrl;
    private List<Offer> offers;

    @Column(name = "username", length = 255, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }
    @Column(name = "password", length = 255)
    public String getPassword() {
        return password;
    }
    @Column(name = "first_name", length = 255)
    public String getFirstName() {
        return firstName;
    }
    @Column(name = "last_name", length = 255)
    public String getLastName() {
        return lastName;
    }
    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    public UserRole getRole() {
        return role;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }
    @OneToMany(mappedBy = "seller")
    public List<Offer> getOffers() {
        return offers;
    }
}

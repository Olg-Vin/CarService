package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.vinio.models.enums.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Table(name = "roles")
public class UserRole extends BaseEntityId{
    private Role role;
//    private List<User> users;


    public UserRole(Role role) {
        this.role = role;
    }

    public UserRole() {

    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }
//    @OneToMany(mappedBy = "role")
//    public List<User> getUsers() {
//        return users;
//    }
}

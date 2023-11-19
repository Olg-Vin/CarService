package org.vinio.dtos;

import lombok.Data;
import lombok.Setter;
import org.vinio.models.UserRole;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private UserRoleDTO role;
    private String imageUrl;

    public String getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

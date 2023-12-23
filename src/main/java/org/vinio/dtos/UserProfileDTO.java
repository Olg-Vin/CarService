package org.vinio.dtos;

import lombok.Data;
import org.vinio.models.UserRole;

@Data
public class UserProfileDTO {
    private String username; // Проверка на уникальность
    private String firstName;
    private String lastName;
    private UserRoleDTO role;
    private String imageUrl;

    public UserProfileDTO(String username, String firstName, String lastName, UserRoleDTO role, String imageUrl) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.imageUrl = imageUrl;
    }
}

package org.vinio.dtos;

import lombok.Data;
import org.vinio.models.UserRole;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private UserRoleDTO role;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;
}

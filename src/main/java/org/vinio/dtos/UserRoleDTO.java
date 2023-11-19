package org.vinio.dtos;

import lombok.Data;
import lombok.Setter;
import org.vinio.models.enums.Role;

import java.util.UUID;

@Setter
public class UserRoleDTO {
    private String id;
    private Role role;

    public String getId() {
        return id;
    }
    public Role getRole() {
        return role;
    }
}

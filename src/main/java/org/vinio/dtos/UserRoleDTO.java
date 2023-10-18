package org.vinio.dtos;

import lombok.Data;
import org.vinio.models.enums.Role;

import java.util.UUID;

@Data
public class UserRoleDTO {
    private UUID id;
    private Role role;
}

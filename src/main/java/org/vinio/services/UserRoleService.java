package org.vinio.services;

import org.vinio.dtos.UserRoleDTO;

import java.util.UUID;

public interface UserRoleService<ID> {
//    crud
    void save(UserRoleDTO userRoleDTO);
    UUID saveAndGetId(UserRoleDTO userRoleDTO);
    UserRoleDTO get(ID id);
    void update(UserRoleDTO userRoleDTO);
    void delete(ID id);
}

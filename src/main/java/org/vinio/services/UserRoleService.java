package org.vinio.services;

import org.vinio.dtos.UserRoleDTO;

public interface UserRoleService<ID> {
//    crud
    void save(UserRoleDTO userRoleDTO);
    String saveAndGetId(UserRoleDTO userRoleDTO);
    UserRoleDTO get(ID id);
    void update(UserRoleDTO userRoleDTO);
    void delete(ID id);
}

package org.vinio.services;

import org.vinio.dtos.UserRoleDTO;

public interface UserRoleService<ID> {
//    crud
    void add(UserRoleDTO userRoleDTO);
    String addUserRole(UserRoleDTO userRoleDTO);
    UserRoleDTO get(ID id);
    void updateUserRole(UserRoleDTO userRoleDTO);
    void removeUserRole(ID id);
}

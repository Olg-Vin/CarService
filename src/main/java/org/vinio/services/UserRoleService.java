package org.vinio.services;

import org.vinio.dtos.UserRoleDTO;

public interface UserRoleService<ID> {
//    crud
    void Save(UserRoleDTO userRoleDTO);
    UserRoleDTO get(ID id);
    void Update(UserRoleDTO brandDTO);
    void Delete(ID id);
}

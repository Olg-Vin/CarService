package org.vinio.services;

import org.vinio.dtos.UserDTO;

public interface UserService<ID> {
//    crud
    void Save(UserDTO userDTO);
    UserDTO get(ID id);
    void Update(UserDTO user);
    void Delete(ID id);
//    add connection
    void addUserRole(ID id);
}

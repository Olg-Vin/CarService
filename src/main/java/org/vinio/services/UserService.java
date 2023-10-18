package org.vinio.services;

import org.vinio.dtos.UserDTO;

public interface UserService<ID> {
//    crud
    void save(UserDTO userDTO);
    UserDTO get(ID id);
    void update(UserDTO user);
    void delete(ID id);
}

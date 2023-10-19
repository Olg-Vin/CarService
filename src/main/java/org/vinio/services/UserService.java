package org.vinio.services;

import org.vinio.dtos.UserDTO;

import java.util.UUID;

public interface UserService<ID> {
//    crud
    void save(UserDTO userDTO);
    UUID saveAndGetId(UserDTO userDTO);
    UserDTO get(ID id);
    void update(UserDTO user);
    void delete(ID id);
}

package org.vinio.services;

import org.vinio.dtos.UserDTO;

import java.util.List;

public interface UserService<ID> {
//    crud
    void save(UserDTO userDTO);
    UserDTO saveAndGetId(UserDTO userDTO);
    UserDTO get(ID id);
    List<UserDTO> getAllUsers();
    void update(UserDTO user);
    void delete(ID id);
}

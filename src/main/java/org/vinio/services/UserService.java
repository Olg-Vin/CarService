package org.vinio.services;

import org.vinio.dtos.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService<ID> {
//    crud
    void save(UserDTO userDTO);
    UserDTO saveAndGetId(UserDTO userDTO);
    UserDTO get(ID id);
    List<UserDTO> getAll();
    void update(UserDTO user);
    void delete(ID id);
}

package org.vinio.services;

import org.vinio.dtos.UserDTO;

import java.util.List;

public interface UserService<ID> {
//    crud
    void add(UserDTO userDTO);
    UserDTO addUser(UserDTO userDTO);
    UserDTO get(ID id);
    List<UserDTO> getAllUsers();
    void updateUser(UserDTO user);
    void removeUser(ID id);
}

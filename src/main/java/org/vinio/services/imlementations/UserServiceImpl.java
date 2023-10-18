package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.vinio.dtos.UserDTO;
import org.vinio.repositories.UserRepository;
import org.vinio.repositories.UserRoleRepository;
import org.vinio.services.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService<UUID> {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
                           UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public UserDTO get(UUID uuid) {
        return null;
    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(UUID uuid) {

    }


}

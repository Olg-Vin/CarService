package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.vinio.dtos.OfferDTO;
import org.vinio.dtos.UserDTO;
import org.vinio.models.Model;
import org.vinio.models.User;
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
        try {userRepository.save(modelMapper.map(userDTO, User.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public UserDTO get(UUID uuid) {
        try {
            return modelMapper.map(userRepository.findById(uuid), UserDTO.class);
        }catch (Exception e) {
            throw new IllegalArgumentException("Объекта offer с id " + uuid + " не существует");
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        save(userDTO);
    }

    @Override
    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }


}

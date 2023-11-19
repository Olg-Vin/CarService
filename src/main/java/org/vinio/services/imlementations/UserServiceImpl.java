package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.OfferDTO;
import org.vinio.dtos.UserDTO;
import org.vinio.models.Model;
import org.vinio.models.User;
import org.vinio.repositories.UserRepository;
import org.vinio.repositories.UserRoleRepository;
import org.vinio.services.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService<String> {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

    }

    @Override
    public void save(UserDTO userDTO) {
        try {userRepository.save(modelMapper.map(userDTO, User.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());}
    }

    @Override
    public UserDTO saveAndGetId(UserDTO userDTO) {
        try {return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)), UserDTO.class);}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());return null;}
    }

    @Override
    public UserDTO get(String uuid) {
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
    public void delete(String uuid) {
        userRepository.deleteById(uuid);
    }


}

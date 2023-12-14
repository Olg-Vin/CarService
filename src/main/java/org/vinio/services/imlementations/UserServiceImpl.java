package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.OfferDTO;
import org.vinio.dtos.UserDTO;
import org.vinio.models.Brand;
import org.vinio.models.Model;
import org.vinio.models.User;
import org.vinio.repositories.UserRepository;
import org.vinio.repositories.UserRoleRepository;
import org.vinio.services.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Cacheable("users")
    public List<UserDTO> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void updateUser(UserDTO userDTO) {
        save(userDTO);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void removeUser(String uuid) {
        userRepository.deleteById(uuid);
    }


}

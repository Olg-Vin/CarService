package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.UserRoleDTO;
import org.vinio.models.UserRole;
import org.vinio.repositories.UserRoleRepository;
import org.vinio.services.UserRoleService;

import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService<UUID> {
    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(UserRoleDTO userRoleDTO) {
        try {userRoleRepository.save(modelMapper.map(userRoleDTO, UserRole.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public UUID saveAndGetId(UserRoleDTO userRoleDTO) {
        try {return userRoleRepository.save(modelMapper.map(userRoleDTO, UserRole.class)).getId();}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    @Override
    public UserRoleDTO get(UUID uuid) {
        try {return modelMapper.map(userRoleRepository.findById(uuid), UserRoleDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта brand с id " + uuid + " не существует");
        }
    }

    @Override
    public void update(UserRoleDTO userRoleDTO) {
        save(userRoleDTO);
    }

    @Override
    public void delete(UUID uuid) {
        userRoleRepository.deleteById(uuid);
    }
}

package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.ModelDTO;
import org.vinio.models.Model;
import org.vinio.repositories.BrandRepository;
import org.vinio.repositories.ModelRepository;
import org.vinio.services.ModelService;

import java.util.UUID;

@Service
public class ModelServiceImpl implements ModelService<String > {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ModelDTO modelDTO) {
        try {modelRepository.save(modelMapper.map(modelDTO, Model.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());}
    }

    @Override
    public ModelDTO saveAndGetId(ModelDTO modelDTO) {
        try {return modelMapper.map(modelRepository.save(modelMapper.map(modelDTO, Model.class)), ModelDTO.class);}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());return null;}
    }

    @Override
    public ModelDTO get(String uuid) {
        try {return modelMapper.map(modelRepository.findById(uuid), ModelDTO.class);}
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Объекта model с id " + uuid + " не существует");
        }
    }
    @Override
    public void update(ModelDTO modelDTO) {
        save(modelDTO);}

    @Override
    public void delete(String uuid) {modelRepository.deleteById(uuid);}

}

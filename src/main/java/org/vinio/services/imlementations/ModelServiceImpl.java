package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.ModelDTO;
import org.vinio.models.Model;
import org.vinio.models.enums.Category;
import org.vinio.repositories.ModelRepository;
import org.vinio.services.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class ModelServiceImpl implements ModelService<String > {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void add(ModelDTO modelDTO) {
        try {modelRepository.save(modelMapper.map(modelDTO, Model.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());}
    }

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public ModelDTO addModel(ModelDTO modelDTO) {
        try {return modelMapper.map(modelRepository.save(modelMapper.map(modelDTO, Model.class)), ModelDTO.class);}
        catch (DataAccessException e){System.out.println("Ошибка сохранения " + e.getMessage());return null;}
    }

    @Override
    public ModelDTO getModel(String uuid) {
        try {return modelMapper.map(modelRepository.findById(uuid), ModelDTO.class);}
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Объекта model с id " + uuid + " не существует");
        }
    }

    @Override
    @Cacheable("models")
    public List<ModelDTO> getAllModels() {
        List<Model> list = modelRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e, ModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void updateModel(ModelDTO modelDTO) {
        add(modelDTO);}

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void removeModel(String uuid) {modelRepository.deleteById(uuid);}

    @Override
    public List<ModelDTO> getModelsByBrandId(String id) {
        List<Model> list = modelRepository.getModelsByBrandId(id);
        return list.stream()
                .map(e->modelMapper.map(e, ModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDTO> getModelsByCategory(Category category) {
        List<Model> list = modelRepository.getModelsByCategory(category);
        return list.stream()
                .map(e->modelMapper.map(e, ModelDTO.class))
                .collect(Collectors.toList());
    }

}

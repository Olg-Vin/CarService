package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.vinio.dtos.ModelDTO;
import org.vinio.models.Model;
import org.vinio.repositories.ModelRepository;
import org.vinio.services.ModelService;

import java.util.UUID;

public class ModelServiceImpl implements ModelService<UUID> {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Save(ModelDTO modelDTO) {modelRepository.save(modelMapper.map(modelDTO, Model.class));}

    @Override
    public ModelDTO get(UUID uuid) {
        return null;
    }

    @Override
    public void Update(ModelDTO modelDTO) {

    }

    @Override
    public void Delete(UUID uuid) {

    }

    @Override
    public void addBrand(UUID uuid) {

    }
}

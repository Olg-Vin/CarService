package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.vinio.dtos.ModelDTO;
import org.vinio.models.Model;
import org.vinio.repositories.BrandRepository;
import org.vinio.repositories.ModelRepository;
import org.vinio.services.ModelService;

import java.util.UUID;

public class ModelServiceImpl implements ModelService<UUID> {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public void save(ModelDTO modelDTO) {
        try {modelRepository.save(modelMapper.map(modelDTO, Model.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }
    @Override
    public ModelDTO get(UUID uuid) {
        try {return modelMapper.map(modelRepository.findById(uuid), ModelDTO.class);}
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Объекта model с id " + uuid + " не существует");
        }
    }
    @Override
    public void update(ModelDTO modelDTO) {
        save(modelDTO);}

    @Override
    public void delete(UUID uuid) {modelRepository.deleteById(uuid);}

    /*@Override
    public void addBrand(UUID brandId, ModelDTO modelDTO) {
        Brand brand = brandRepository.findById(brandId).orElseThrow();
        if (brand != null) {
            Model model = modelMapper.map(modelDTO, Model.class);
            model.setBrand(brand);
            modelRepository.save(model);
        } else {
            throw new IllegalArgumentException("Brand with id " + brandId + " not found");
        }
    }*/
}

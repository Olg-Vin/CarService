package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.BrandDTO;
import org.vinio.models.Brand;
import org.vinio.repositories.BrandRepository;
import org.vinio.services.BrandService;

import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService<UUID> {
    @Autowired
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void save(BrandDTO brandDTO) {
        try {brandRepository.save(modelMapper.map(brandDTO, Brand.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public BrandDTO saveAndGetId(BrandDTO brandDTO) {
        try {
            return modelMapper.map(brandRepository.save(modelMapper.map(brandDTO, Brand.class)), BrandDTO.class);
        }
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    @Override
    public BrandDTO get(UUID uuid) {
        try {return modelMapper.map(brandRepository.findById(uuid), BrandDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта brand с id " + uuid + " не существует");
        }
    }
    @Override
    public void update(BrandDTO brandDTO) {save(brandDTO);}
    @Override
    public void delete(UUID uuid) {
        brandRepository.deleteById(uuid);
    }
}

package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.BrandDTO;
import org.vinio.models.Brand;
import org.vinio.repositories.BrandRepository;
import org.vinio.services.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandServiceImpl implements BrandService<String > {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void add(BrandDTO brandDTO) {
        try {brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brand.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public BrandDTO addBrand(BrandDTO brandDTO) {
        try {
            return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brand.class)), BrandDTO.class);
        }
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    @Override
    public BrandDTO getBrand(String uuid) {
        try {return modelMapper.map(brandRepository.findById(uuid), BrandDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта brand с id " + uuid + " не существует");
        }
    }

    @Override
    @Cacheable("brands")
    public List<BrandDTO> getAllBrands() {
        List<Brand> list = brandRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e,BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void updateBrand(BrandDTO brandDTO) {
        add(brandDTO);}
    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void removeBrand(String uuid) {
        brandRepository.deleteById(uuid);
    }
}

package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.OfferDTO;
import org.vinio.models.Brand;
import org.vinio.models.Offer;
import org.vinio.repositories.BrandRepository;
import org.vinio.services.BrandService;

import java.util.Optional;
import java.util.UUID;

public class BrandServiceImpl implements BrandService<UUID> {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void Save(BrandDTO brandDTO) {
        try {brandRepository.save(modelMapper.map(brandDTO, Brand.class));;}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }
    @Override
    public BrandDTO get(UUID uuid) {
        try {return modelMapper.map(brandRepository.findById(uuid), BrandDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта brand с id " + uuid + " не существует");
        }
    }
    @Override
    public void Update(BrandDTO brandDTO) {Save(brandDTO);}
    @Override
    public void Delete(UUID uuid) {brandRepository.deleteById(uuid);}
}

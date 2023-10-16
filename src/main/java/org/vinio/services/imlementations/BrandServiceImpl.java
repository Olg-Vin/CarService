package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.vinio.dtos.BrandDTO;
import org.vinio.models.Brand;
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
    public void Save(BrandDTO brandDTO) {brandRepository.save(modelMapper.map(brandDTO, Brand.class));}
    @Override
    public Optional<BrandDTO> get(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(uuid), BrandDTO.class));}
    @Override
    public void Update(BrandDTO brandDTO) {Save(brandDTO);}
    @Override
    public void Delete(UUID uuid) {brandRepository.deleteById(uuid);}
}

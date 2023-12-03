package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.BrandDTO;
import org.vinio.models.Brand;
import org.vinio.repositories.BrandRepository;
import org.vinio.services.BrandService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService<String > {
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
        try {brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brand.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public BrandDTO saveAndGetId(BrandDTO brandDTO) {
        try {
            return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brand.class)), BrandDTO.class);
        }
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    @Override
    public BrandDTO get(String uuid) {
        try {return modelMapper.map(brandRepository.findById(uuid), BrandDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта brand с id " + uuid + " не существует");
        }
    }

    @Override
    public List<BrandDTO> getAll() {
        List<Brand> list = brandRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e,BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(BrandDTO brandDTO) {save(brandDTO);}
    @Override
    public void delete(String uuid) {
        brandRepository.deleteById(uuid);
    }
}

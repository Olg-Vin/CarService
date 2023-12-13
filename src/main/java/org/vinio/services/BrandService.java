package org.vinio.services;

import org.vinio.dtos.BrandDTO;

import java.util.List;

public interface BrandService<ID> {
//    crud
    void add(BrandDTO brandDTO);
    BrandDTO addBrand(BrandDTO brandDTO);
    BrandDTO getBrand(ID id);
    List<BrandDTO> getAllBrands();
    void updateBrand(BrandDTO brandDTO);
    void removeBrand(ID id);
}

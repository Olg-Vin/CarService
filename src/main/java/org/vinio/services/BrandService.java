package org.vinio.services;

import org.vinio.dtos.BrandDTO;

import java.util.List;
import java.util.UUID;

public interface BrandService<ID> {
//    crud
    void save(BrandDTO brandDTO);
    BrandDTO saveAndGetId(BrandDTO brandDTO);
    BrandDTO get(ID id);
    List<BrandDTO> getAll();
    void update(BrandDTO brandDTO);
    void delete(ID id);
}

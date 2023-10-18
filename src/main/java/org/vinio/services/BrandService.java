package org.vinio.services;

import org.vinio.dtos.BrandDTO;

public interface BrandService<ID> {
//    crud
    void save(BrandDTO brandDTO);
    BrandDTO get(ID id);
    void update(BrandDTO brandDTO);
    void delete(ID id);
}

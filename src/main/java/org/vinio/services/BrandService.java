package org.vinio.services;

import org.vinio.dtos.BrandDTO;

import java.util.Optional;

public interface BrandService<ID> {
//    crud
    void Save(BrandDTO brandDTO);
    BrandDTO get(ID id);
    void Update(BrandDTO brandDTO);
    void Delete(ID id);
}

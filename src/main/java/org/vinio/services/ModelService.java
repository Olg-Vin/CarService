package org.vinio.services;

import org.vinio.dtos.ModelDTO;

import java.util.UUID;

public interface ModelService<ID> {
//    crud
    void Save(ModelDTO modelDTO);
    ModelDTO get(ID id);
    void Update(ModelDTO modelDTO);
    void Delete(ID id);
//    add connection
    void addBrand(ID brandId, ModelDTO modelDTO);
}

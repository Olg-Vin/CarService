package org.vinio.services;

import org.vinio.dtos.ModelDTO;

import java.util.UUID;

public interface ModelService<ID> {
//    crud
    void save(ModelDTO modelDTO);
    UUID saveAndGetId(ModelDTO modelDTO);
    ModelDTO get(ID id);
    void update(ModelDTO modelDTO);
    void delete(ID id);
}

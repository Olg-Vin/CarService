package org.vinio.services;

import org.vinio.dtos.ModelDTO;

public interface ModelService<ID> {
//    crud
    void save(ModelDTO modelDTO);
    ModelDTO get(ID id);
    void update(ModelDTO modelDTO);
    void delete(ID id);
}

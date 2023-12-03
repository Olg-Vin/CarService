package org.vinio.services;

import org.vinio.dtos.ModelDTO;

import java.util.List;

public interface ModelService<ID> {
    void save(ModelDTO modelDTO);
    ModelDTO saveAndGetId(ModelDTO modelDTO);
    ModelDTO get(ID id);
    List<ModelDTO> getAll();
    void update(ModelDTO modelDTO);
    void delete(ID id);
}

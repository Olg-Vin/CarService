package org.vinio.services;

import org.vinio.dtos.ModelDTO;
import org.vinio.models.enums.Category;

import java.util.List;

public interface ModelService<ID> {
    void add(ModelDTO modelDTO);
    ModelDTO addModel(ModelDTO modelDTO);
    ModelDTO getModel(ID id);
    List<ModelDTO> getAllModels();
    void updateModel(ModelDTO modelDTO);
    void removeModel(ID id);
    List<ModelDTO> getModelsByBrandId(ID id);
    List<ModelDTO> getModelsByCategory(Category category);
}

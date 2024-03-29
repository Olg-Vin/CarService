package org.vinio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vinio.models.Model;
import org.vinio.models.enums.Category;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, String > {
    List<Model> getModelsByBrandId(String id);
    List<Model> getModelsByCategory(Category category);
}

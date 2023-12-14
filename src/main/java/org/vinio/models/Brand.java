package org.vinio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Setter
@Entity
@Table(name = "brands")
public class Brand extends BaseEntityCM {
    private String name;
    private Set<Model> models;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    public String getName() {
        return name;
    }
    @OneToMany(mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    public Set<Model> getModels() {
        return models;
    }
}

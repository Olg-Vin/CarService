package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "brands")
public class Brand extends BaseEntityCM{
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Model> models;
}

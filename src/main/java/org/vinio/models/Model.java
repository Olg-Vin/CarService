package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.vinio.models.enums.Category;
import org.vinio.models.enums.converters.CategoryConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "models")
public class Model extends BaseEntityCM{
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "start_year")
    private int startYear;
    @Column(name = "end_year")
    private int endYear;

//    todo lazy

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    TODO CASCADE TYPE
    @OneToMany(mappedBy = "model")
    private List<Offer> offers;
}

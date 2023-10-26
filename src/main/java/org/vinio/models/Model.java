package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.vinio.models.enums.Category;
import org.vinio.models.enums.converters.CategoryConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
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
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

//    todo lazy

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    TODO CASCADE TYPE
    @OneToMany(mappedBy = "model")
    private List<Offer> offers;
}

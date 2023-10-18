package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.vinio.models.enums.Category;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    //    todo string вместо url??
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_year")
    private LocalDate startYear;
    @Column(name = "end_year")
    private LocalDate endYear;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

//    todo lazy

    @ManyToOne
    @JoinColumn(name = "brand_id", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private Brand brand;

    @OneToMany(mappedBy = "model")
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<Offer> offers;
}

package org.vinio.models;

import jakarta.persistence.*;
import org.vinio.models.enums.Category;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "image_url")
    private URL imageUrl;
    @Column(name = "start_year", nullable = false)
    private LocalDate startYear;
    @Column(name = "end_year", nullable = false)
    private LocalDate endYear;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;
    @OneToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
}

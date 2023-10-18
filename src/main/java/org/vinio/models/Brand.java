package org.vinio.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;
    @OneToMany(mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Model> models;
}

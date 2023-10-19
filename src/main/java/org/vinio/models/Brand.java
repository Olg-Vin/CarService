package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
//    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<Model> models;
}

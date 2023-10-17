package org.vinio.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;
}

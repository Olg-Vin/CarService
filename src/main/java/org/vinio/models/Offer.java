package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Entity
@Data
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "engine")
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "mileage")
    private int mileage;
    //    todo decimal
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column(name = "year")
    private Year year;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    //    todo lazy

    @ManyToOne
    @JoinColumn(name = "model_id", unique = true)
    private Model model;
    @ManyToOne
    @JoinColumn(name = "seller_id", unique = true)
    private User seller;
}


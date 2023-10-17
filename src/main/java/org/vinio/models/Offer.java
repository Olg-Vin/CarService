package org.vinio.models;

import jakarta.persistence.*;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "engine")
    private Engine engine;
    @Column(name = "image_url")
    private URL imageUrl;
    @Column(name = "mileage")
    private int mileage;
//    todo decimal
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "transmission")
    private Transmission transmission;
    @Column(name = "year")
    private Year year;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    //    todo lazy


    @OneToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}


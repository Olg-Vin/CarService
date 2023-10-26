package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Entity
@Data
@Table(name = "offers")
public class Offer extends BaseEntityCM{

    @Column(name = "description")
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

    //    todo lazy

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}


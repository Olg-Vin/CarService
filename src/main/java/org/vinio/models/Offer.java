package org.vinio.models;

import jakarta.persistence.*;
import lombok.Data;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Data
@Table(name = "offers")
public class Offer extends BaseEntityCM {
    private String description;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private Year year;
    private Model model;
    private User seller;

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    @Column(name = "engine")
    @Enumerated(EnumType.STRING)
    public Engine getEngine() {
        return engine;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }
    @Column(name = "price")
    public int getMileage() {
        return mileage;
    }
    @Column(name = "mileage")
    public BigDecimal getPrice() {
        return price;
    }
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    public Transmission getTransmission() {
        return transmission;
    }
    @Column(name = "year")
    public Year getYear() {
        return year;
    }
    @ManyToOne
    @JoinColumn(name = "model_id")
    public Model getModel() {
        return model;
    }
    @ManyToOne
    @JoinColumn(name = "seller_id")
    public User getSeller() {
        return seller;
    }
}


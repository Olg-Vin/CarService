package org.vinio.dtos;

import lombok.Data;
import lombok.Setter;
import org.vinio.models.Model;
import org.vinio.models.User;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Setter
public class OfferDTO {
    private String id;
    private String description;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private Year year;
    private ModelDTO model;
    private UserDTO seller;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Year getYear() {
        return year;
    }

    public ModelDTO getModel() {
        return model;
    }

    public UserDTO getSeller() {
        return seller;
    }
}

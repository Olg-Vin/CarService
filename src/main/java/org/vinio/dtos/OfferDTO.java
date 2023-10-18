package org.vinio.dtos;

import lombok.Data;
import org.vinio.models.Model;
import org.vinio.models.User;
import org.vinio.models.enums.Engine;
import org.vinio.models.enums.Transmission;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Data
public class OfferDTO {
    private UUID id;
    private String description;
    private Engine engine;
    private URL imageUrl;
    private int mileage;
    //    todo decimal
    private BigDecimal price;
    private Transmission transmission;
    private Year year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Model model;
    private User seller;
}

package org.vinio.dtos;

import lombok.Data;
import org.vinio.models.Brand;
import org.vinio.models.enums.Category;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ModelDTO {
    private UUID id;
    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private BrandDTO brand;
}


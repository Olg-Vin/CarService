package org.vinio.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


// излишние поля убрать по логике отображения пользователю
@Data
public class BrandDTO {
    private UUID id;
    private String name;
}

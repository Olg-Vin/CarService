package org.vinio.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@Setter
public class BrandDTO {
    private String id;
    private String name;


    public String getId() {
        return id;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, max = 255)
    public String getName() {
        return name;
    }
}

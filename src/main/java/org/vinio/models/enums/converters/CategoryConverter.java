package org.vinio.models.enums.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.vinio.models.enums.Category;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category attribute) {
        if (attribute == null) {
            return null;
        }
        System.out.println("CONVERT " + attribute.getCategory());
        return attribute.getCategory();
    }

    @Override
    public Category convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(Category.values())
                .filter(c -> c.getCategory() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

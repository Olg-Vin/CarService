package org.vinio.models;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.vinio.models.enums.Category;
import org.vinio.models.enums.converters.CategoryConverter;

import java.util.List;

@Entity
@Setter
@Table(name = "models")
public class Model extends BaseEntityCM {
    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private Brand brand;
    private List<Offer> offers;

    @Column(name = "name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category")
    public Category getCategory() {
        return category;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(name = "start_year")
    public int getStartYear() {
        return startYear;
    }

    @Column(name = "end_year")
    public int getEndYear() {
        return endYear;
    }

    //    todo lazy
    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand getBrand() {
        return brand;
    }

    //    TODO CASCADE TYPE
    @OneToMany(mappedBy = "model")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    public List<Offer> getOffers() {
        return offers;
    }
}

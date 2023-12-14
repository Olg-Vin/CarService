package org.vinio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vinio.models.Offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String > {
    @Query("SELECT f FROM Offer f ORDER BY f.price DESC LIMIT 3")
    List<Offer> getOfferSortedByPrice();
    @Query("SELECT AVG(f.price) FROM Offer f")
    BigDecimal findAveragePrice();
    List<Offer> findOfferByModelId(String id);
}

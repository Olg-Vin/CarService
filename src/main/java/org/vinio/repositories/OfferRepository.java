package org.vinio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vinio.models.Offer;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String > {
}

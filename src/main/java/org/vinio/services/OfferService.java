package org.vinio.services;

import org.vinio.dtos.OfferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService<ID> {
//    crud
    void add(OfferDTO offerDTO);
    OfferDTO addOffer(OfferDTO offerDTO);
    OfferDTO getOffer(ID id);
    List<OfferDTO> getAllOffers();
    void updateOffer(OfferDTO offerDTO);
    void removeOffer(ID id);
    List<OfferDTO> getOfferSortedByPrice();
    BigDecimal getMidlPrice();
    List<OfferDTO> findOfferByModelId(String id);
}

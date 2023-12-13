package org.vinio.services;

import org.vinio.dtos.OfferDTO;

import java.util.List;

public interface OfferService<ID> {
//    crud
    void add(OfferDTO offerDTO);
    OfferDTO addOffer(OfferDTO offerDTO);
    OfferDTO getOffer(ID id);
    List<OfferDTO> getAllOffers();
    void update(OfferDTO offerDTO);
    void delete(ID id);

}

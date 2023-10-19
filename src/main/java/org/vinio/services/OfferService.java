package org.vinio.services;

import org.vinio.dtos.OfferDTO;

import java.util.UUID;

public interface OfferService<ID> {
//    crud
    void save(OfferDTO offerDTO);
    OfferDTO saveAndGetId(OfferDTO offerDTO);
    OfferDTO get(ID id);
    void update(OfferDTO offerDTO);
    void delete(ID id);

}

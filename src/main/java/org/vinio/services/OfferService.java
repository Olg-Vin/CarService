package org.vinio.services;

import org.vinio.dtos.OfferDTO;

import java.util.List;
import java.util.UUID;

public interface OfferService<ID> {
//    crud
    void save(OfferDTO offerDTO);
    OfferDTO saveAndGetId(OfferDTO offerDTO);
    OfferDTO get(ID id);
    List<OfferDTO> getAll();
    void update(OfferDTO offerDTO);
    void delete(ID id);

}

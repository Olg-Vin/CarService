package org.vinio.services;

import org.vinio.dtos.OfferDTO;

public interface OfferService<ID> {
//    crud
    void Save(OfferDTO offerDTO);
    OfferDTO get(ID id);
    void Update(OfferDTO offerDTO);
    void Delete(ID id);
//    add connection
    void addModel(ID id);
    void addUser(ID id);
}

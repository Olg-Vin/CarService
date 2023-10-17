package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.vinio.dtos.OfferDTO;
import org.vinio.models.Offer;
import org.vinio.repositories.ModelRepository;
import org.vinio.repositories.OfferRepository;
import org.vinio.repositories.UserRepository;
import org.vinio.services.OfferService;

import java.util.UUID;

public class OfferServiceImpl implements OfferService<UUID> {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository,
                            ModelRepository modelRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void Save(OfferDTO offerDTO) {
//        try {offerRepository.save(modelMapper.map(offerDTO, Offer.class));}
//        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

// todo referenceById
    @Override
    public OfferDTO get(UUID uuid) {
        try {return modelMapper.map(offerRepository.findById(uuid),OfferDTO.class);}
        catch (Exception e){
            throw new IllegalArgumentException("Объекта offer с id " + uuid + " не существует");
        }
    }
    @Override
    public void Update(OfferDTO offerDTO) {
        Save(offerDTO);
    }

    @Override
    public void Delete(UUID uuid) {
        offerRepository.deleteById(uuid);
    }

    @Override
    public void addModel(UUID uuid) {

    }

    @Override
    public void addUser(UUID uuid) {

    }
}

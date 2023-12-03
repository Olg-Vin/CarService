package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.OfferDTO;
import org.vinio.models.Brand;
import org.vinio.models.Offer;
import org.vinio.repositories.ModelRepository;
import org.vinio.repositories.OfferRepository;
import org.vinio.repositories.UserRepository;
import org.vinio.services.OfferService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }

    @Override
    public void save(OfferDTO offerDTO) {
        try {offerRepository.save(modelMapper.map(offerDTO, Offer.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    public OfferDTO saveAndGetId(OfferDTO offerDTO) {
        try {return modelMapper.map(offerRepository.save(modelMapper.map(offerDTO, Offer.class)), OfferDTO.class);}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    // todo referenceById
    @Override
    public OfferDTO get(String uuid) {
        try {
            return modelMapper.map(offerRepository.findById(uuid), OfferDTO.class);
        }catch (Exception e) {
            throw new IllegalArgumentException("Объекта offer с id " + uuid + " не существует");
        }
    }

    @Override
    public List<OfferDTO> getAll() {
        List<Offer> list = offerRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(OfferDTO offerDTO) {
        save(offerDTO);
    }

    @Override
    public void delete(String uuid) {
        offerRepository.deleteById(uuid);
    }


}

package org.vinio.services.imlementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinio.dtos.OfferDTO;
import org.vinio.models.Offer;
import org.vinio.repositories.OfferRepository;
import org.vinio.services.OfferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void add(OfferDTO offerDTO) {
        try {offerRepository.save(modelMapper.map(offerDTO, Offer.class));}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");}
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public OfferDTO addOffer(OfferDTO offerDTO) {
        try {return modelMapper.map(offerRepository.save(modelMapper.map(offerDTO, Offer.class)), OfferDTO.class);}
        catch (DataAccessException e){System.out.println("Ошибка сохранения");return null;}
    }

    // todo referenceById
    @Override
    public OfferDTO getOffer(String uuid) {
        try {
            return modelMapper.map(offerRepository.findById(uuid), OfferDTO.class);
        }catch (Exception e) {
            throw new IllegalArgumentException("Объекта offer с id " + uuid + " не существует");
        }
    }

    @Override
    @Cacheable("offers")
    public List<OfferDTO> getAllOffers() {
        List<Offer> list = offerRepository.findAll();
        return list.stream()
                .map(e->modelMapper.map(e, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void updateOffer(OfferDTO offerDTO) {
        add(offerDTO);
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void removeOffer(String uuid) {
        offerRepository.deleteById(uuid);
    }

    @Override
    public List<OfferDTO> getOfferSortedByPrice() {
        List<Offer> list = offerRepository.getOfferSortedByPrice();
        return list.stream()
                .map(e->modelMapper.map(e, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getMidlPrice() {
        return offerRepository.findAveragePrice();
    }

    @Override
    public List<OfferDTO> findOfferByModelId(String id) {
        List<Offer> list = offerRepository.findOfferByModelId(id);
        return list.stream()
                .map(e->modelMapper.map(e, OfferDTO.class))
                .collect(Collectors.toList());
    }
}

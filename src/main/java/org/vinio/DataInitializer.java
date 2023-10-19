package org.vinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.ModelDTO;
import org.vinio.dtos.UserDTO;
import org.vinio.dtos.UserRoleDTO;
import org.vinio.models.enums.Role;
import org.vinio.services.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService brandService;
    private final ModelService modelService;
    private final OfferService offerService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public DataInitializer(BrandService brandService, ModelService modelService,
                           OfferService offerService, UserService userService,
                           UserRoleService userRoleService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) {
        List<BrandDTO> brandDTOS = addBrands();
        addModels(brandDTOS);
//        brandService.delete(brandDTOS.get(0).getId());
        addUsers();
    }
    private List<BrandDTO> addBrands(){
        List<BrandDTO> list = new ArrayList<>();
        String[] names = new String[]{"BMW", "Lada", "Audi","Mini","Kia"};
        for (String s:names){
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(s);
            brandDTO.setCreated(LocalDateTime.now());
            brandDTO.setModified(LocalDateTime.now());
            list.add(brandService.saveAndGetId(brandDTO));
//            list.add(brandDTO);
        }
        return list;
    }
    private List<ModelDTO> addModels(List<BrandDTO> brandDTOS){
        List<ModelDTO> list = new ArrayList<>();
        String[] names = new String[]{"rt56", "shy7", "sj-j7","shy6","s7yf",
                                        "jhf65", "jg54d", "oj-u7"};
        for (String s:names){
            ModelDTO modelDTO = new ModelDTO();
            modelDTO.setName(s);
            modelDTO.setEndYear(LocalDate.now());
            modelDTO.setCreated(LocalDateTime.now());
            modelDTO.setModified(LocalDateTime.now());
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            modelDTO.setBrand(brandDTOS.get(--randomNumber));
            list.add(modelService.saveAndGetId(modelDTO));
        }
        return list;
    }

    private List<UserDTO> addUsers(){
        int i = 0;
        List<UserDTO> list = new ArrayList<>();
        String[] names = new String[]{"Mirio", "Uraraca", "Shou","Kenma","Itto"};
        for (String s:names){
            UserDTO userDTO = new UserDTO();
            userDTO.setActive(true);
            userDTO.setCreated(LocalDateTime.now());
            userDTO.setModified(LocalDateTime.now());
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRole(Role.valueOf("User"));
            userDTO.setRole(userRoleDTO);
            userDTO.setUsername(names[i]);
            userService.save(userDTO);
            i++;
        }
        return list;
    }
    /*
    // Создание начальных данных для Offer
    Offer offer1 = new Offer();
        offer1.setDescription("This is a description for offer one.");
        offer1.setEngine(Offer.Engine.GASOLINE);
        offer1.setImageUrl("http://example.com/offer1.jpg");
        offer1.setMileage(25000);
        offer1.setPrice(30000.00);
        offer1.setTransmission(Offer.Transmission.AUTOMATIC);
        offer1.setYear(2010);
        offer1.setCreated(new Date());
        offer1.setModified(new Date());
        offer1.setModel(model1);
        offer1.setSeller(user1);
        offerService.save(offer1);

    Offer offer2 = new Offer();
        offer2.setDescription("This is a description for offer two.");
        offer2.setEngine(Offer.Engine.DIESEL);
        offer2.setImageUrl("http://example.com/offer2.jpg");
        offer2.setMileage(10000);
        offer2.setPrice(45000.00);
        offer2.setTransmission(Offer.Transmission.MANUAL);
        offer2.setYear(2015);
        offer2.setCreated(new Date());
        offer2.setModified(new Date());
        offer2.setModel(model2);
        offer2.setSeller(user2);
        offerService.save(offer2);*/
}

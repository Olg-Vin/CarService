package org.vinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vinio.dtos.BrandDTO;
import org.vinio.services.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        addBrands();
    }

    public void addBrands(){
        String[] names = new String[]{"BMW", "Lada", "Audi","Mini","Kia"};
        for (String s:names){
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(s);
            brandDTO.setCreated(LocalDateTime.now());
            brandDTO.setModified(LocalDateTime.now());
            brandService.save(brandDTO);
        }
    }
    /*Brand brand1 = new Brand();
        brand1.setName("Brand One");
        brand1.setCreated(new Date());
        brand1.setModified(new Date());
        brandService.save(brand1);

    Brand brand2 = new Brand();
        brand2.setName("Brand Two");
        brand2.setCreated(new Date());
        brand2.setModified(new Date());
        brandService.save(brand2);

    UserRole userRole1 = new UserRole();
        userRole1.setRole(UserRole.Role.USER);
        userRoleService.save(userRole1);

    UserRole userRole2 = new UserRole();
        userRole2.setRole(UserRole.Role.ADMIN);
        userRoleService.save(userRole2);

    User user2 = new User();
        user2.setUsername("usertwo");
        user2.setPassword("password");
        user2.setFirstName("User");
        user2.setLastName("Two");
        user2.setIsActive(false);
        user2.setUserRole(userRole2);
        user2.setImageUrl("http://example.com/avatar2.jpg");
        user2.setCreated(new Date());
        user2.setModified(new Date());
        userService.save(user2);

    Model model1 = new Model();
        model1.setName("Model One");
        model1.setCategory(Model.Category.CAR);
        model1.setImageUrl("http://example.com/image1.jpg");
        model1.setStartYear(2000);
        model1.setEndYear(2010);
        model1.setCreated(new Date());
        model1.setModified(new Date());
        model1.setBrand(brand1);
        modelService.save(model1);

    Model model2 = new Model();
        model2.setName("Model Two");
        model2.setCategory(Model.Category.TRUCK);
        model2.setImageUrl("http://example.com/image2.jpg");
        model2.setStartYear(2005);
        model2.setEndYear(2020);
        model2.setCreated(new Date());
        model2.setModified(new Date());
        model2.setBrand(brand2);
        modelService.save(model2);

    User user1 = new User();
        user1.setUsername("userone");
        user1.setPassword("password");
        user1.setFirstName("User");
        user1.setLastName("One");
        user1.setIsActive(true);
        user1.setUserRole(userRole1);
        user1.setImageUrl("http://example.com/avatar1.jpg");
        user1.setCreated(new Date());
        user1.setModified(new Date());
        userService.save(user1);

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

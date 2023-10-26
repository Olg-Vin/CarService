package org.vinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vinio.dtos.*;
import org.vinio.models.enums.Category;
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
    private final BrandService<UUID> brandService;
    private final ModelService<UUID> modelService;
    private final OfferService<UUID> offerService;
    private final UserService<UUID> userService;
    private final UserRoleService<UUID> userRoleService;

    @Autowired
    public DataInitializer(BrandService<UUID> brandService, ModelService<UUID> modelService,
                           OfferService<UUID> offerService, UserService<UUID> userService,
                           UserRoleService<UUID> userRoleService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) {
        List<BrandDTO> brandDTOS = addBrands();
        List<ModelDTO> modelDTOS = addModels(brandDTOS);
//        brandService.delete(brandDTOS.get(0).getId());
        List<UserDTO> userDTOS = addUsers();
        addOffers(userDTOS, modelDTOS);

        printSomething(modelDTOS.get(0).getId());
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
            modelDTO.setEndYear(LocalDate.now().getYear());
            modelDTO.setCreated(LocalDateTime.now());
            modelDTO.setModified(LocalDateTime.now());
            modelDTO.setCategory(Category.Motorcycle);
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            modelDTO.setBrand(brandDTOS.get(--randomNumber));
            modelDTO.setStartYear(LocalDate.now().getYear());
            list.add(modelService.saveAndGetId(modelDTO));
        }
        return list;
    }

    private List<UserDTO> addUsers(){
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
            userDTO.setUsername(s);
            list.add(userService.saveAndGetId(userDTO));
        }
        return list;
    }
    private List<OfferDTO> addOffers(List<UserDTO> userDTOList, List<ModelDTO> modelDTOList){
        int i = 0;
        List<OfferDTO> list = new ArrayList<>();
        String[] names = new String[]{"text1", "text2", "text3","text4","text5"};
        for (String s:names){
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(s);
            offerDTO.setModel(modelDTOList.get(i));
            offerDTO.setSeller(userDTOList.get(i));
            i++;
            list.add(offerService.saveAndGetId(offerDTO));
        }
        return list;
    }
    private void printSomething(UUID modelsUuid){
        System.out.println(modelService.get(modelsUuid));
    }
}

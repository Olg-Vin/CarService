package org.vinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vinio.dtos.*;
import org.vinio.models.enums.Category;
import org.vinio.models.enums.Role;
import org.vinio.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService<String> brandService;
    private final ModelService<String> modelService;
    private final OfferService<String> offerService;
    private final UserService<String> userService;
    private final UserRoleService<String> userRoleService;

    @Autowired
    public DataInitializer(BrandService<String> brandService, ModelService<String> modelService,
                           OfferService<String> offerService, UserService<String> userService,
                           UserRoleService<String> userRoleService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) {
//        addManyBrands();
        List<BrandDTO> brandDTOS = addBrands();
        System.out.println();
        System.out.println(brandDTOS.get(0).toString());
        System.out.println();
        List<ModelDTO> modelDTOS = addModels(brandDTOS);
//        brandService.delete(brandDTOS.get(0).getId());
        List<UserDTO> userDTOS = addUsers();
        addOffers(userDTOS, modelDTOS);
        System.out.println();
        System.out.println(brandDTOS.get(0).toString());
        System.out.println();
        System.out.println(modelDTOS.get(0).toString());
    }
    private List<BrandDTO> addBrands(){
        List<BrandDTO> list = new ArrayList<>();
        String[] names = new String[]{"BMW", "Lada", "Audi","Mini","Kia"};
        for (String s:names){
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(s);
            list.add(brandService.addBrand(brandDTO));
//            list.add(brandDTO);
        }
        return list;
    }
    private void addManyBrands(){
        for (int i = 0; i < 30_000; i++){
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName("car" + i);
            brandService.addBrand(brandDTO);
        }
    }
    private List<ModelDTO> addModels(List<BrandDTO> brandDTOS){
        List<ModelDTO> list = new ArrayList<>();
        String[] names = new String[]{"rt56", "shy7", "sj-j7","shy6","s7yf",
                                        "jhf65", "jg54d", "oj-u7"};
        for (String s:names){
            ModelDTO modelDTO = new ModelDTO();
            modelDTO.setName(s);
            modelDTO.setEndYear(LocalDate.now().getYear());
            modelDTO.setCategory(Category.Motorcycle);
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            modelDTO.setBrand(brandDTOS.get(--randomNumber));
            modelDTO.setStartYear(LocalDate.now().getYear());
            list.add(modelService.addModel(modelDTO));
        }
        return list;
    }

    private List<UserDTO> addUsers(){
        List<UserDTO> list = new ArrayList<>();
        String[] names = new String[]{"Mirio", "Uraraca", "Shou","Kenma","Itto"};
        for (String s:names){
            UserDTO userDTO = new UserDTO();
            userDTO.setActive(true);
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
            list.add(offerService.addOffer(offerDTO));
        }
        return list;
    }
}

package org.vinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vinio.services.BrandService;
import org.vinio.services.ModelService;
import org.vinio.services.OfferService;
import org.vinio.services.UserService;
import org.vinio.services.imlementations.BrandServiceImpl;
import org.vinio.services.imlementations.ModelServiceImpl;
import org.vinio.services.imlementations.OfferServiceImpl;
import org.vinio.services.imlementations.UserServiceImpl;

@Component
public class DataInitializer implements CommandLineRunner {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BrandService brandService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ModelService modelService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private OfferService offerService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {


    }
}

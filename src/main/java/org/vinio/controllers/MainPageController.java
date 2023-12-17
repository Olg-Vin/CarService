package org.vinio.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vinio.services.imlementations.OfferServiceImpl;

@Controller
@RequestMapping("/main")
public class MainPageController {
    private OfferServiceImpl offerService;

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @GetMapping("")
    public String mainPage(Model model){
        LOG.log(Level.INFO, "open home-page");
        model.addAttribute("offers",offerService.getOfferSortedByPrice());
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(Model model){
        LOG.log(Level.INFO, "open login-page");
        model.addAttribute("object", "login");
        return "auth";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
        LOG.log(Level.INFO, "open register-page");
        model.addAttribute("object", "register");
        return "auth";
    }
}

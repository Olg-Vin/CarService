package org.vinio.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vinio.dtos.OfferDTO;
import org.vinio.services.imlementations.OfferServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferServiceImpl offerService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/get/{id}")
    public String getOffer(@PathVariable String id, Model model, Principal principal){
        LOG.log(Level.INFO, "User with " + principal.getName() +" get offer with id " + id);
        model.addAttribute("offer",offerService.getOffer(id));
        return "/cards/offer-card";
    }
    @GetMapping("/getByModel/{id}")
    public String findOfferByModelId(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Get offers for model with id " + id);
        model.addAttribute("offers", offerService.findOfferByModelId(id));
        return "offer-all";
    }
    @GetMapping("/getAll")
    public String getAllOffers(Model model, Principal principal){
        LOG.log(Level.INFO, principal == null ?
                "Show all offers" : "User with name " +
                principal.getName() + " get info for all offers");
        model.addAttribute("offers",offerService.getAllOffers());
        return "offer-all";
    }


    @GetMapping("/add")
    public String addOffer(Model model){
        LOG.log(Level.INFO, "*principal* call page-add for offer");
        model.addAttribute("object", "offer");
        return "page-add";
    }
    @ModelAttribute("offerDTO")
    public OfferDTO initOffer() {
        return new OfferDTO();
    }
    @PostMapping("/add")
    public String addOffers(@Valid OfferDTO offerDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        LOG.log(Level.INFO, "*principal* tried to add new offer");
        if (bindingResult.hasErrors()) {
            LOG.log(Level.INFO, "*principal* has unsuccessful attempt to add offer");
            redirectAttributes.addFlashAttribute("offerDTO", offerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO",
                    bindingResult);
            return "redirect:/offers/add";
        }
        LOG.log(Level.INFO, "*principal* add new offer");
        offerService.add(offerDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffers(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* delete brand with id "+id);
        offerService.removeOffer(id);
        return "redirect:/main";
    }

    @PostMapping("/update/{id}")
    public String updateOffers(Model model, @PathVariable String id){
        LOG.log(Level.INFO, "*principal* tried to update model with id "+id);
        model.addAttribute("object", "offer");
        model.addAttribute("brandDTO", offerService.getOffer(id));
        return "redirect:/main";
    }
}

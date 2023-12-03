package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.OfferDTO;
import org.vinio.services.imlementations.OfferServiceImpl;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferServiceImpl offerService;
    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/get/{id}")
    public String getOffers(@PathVariable String id, Model model){
        model.addAttribute("offer",offerService.get(id));
        return "offer";
    }
    @GetMapping("/getAll")
    public String getAllBrands(Model model){
        model.addAttribute("offer",offerService.getAll());
        return "offer";
    }

    @PostMapping("/add")
    public String addOffers(@Valid @RequestBody OfferDTO offerDTO, Model model){
        offerService.save(offerDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffers(@PathVariable String id, Model model){
        offerService.delete(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateOffers(@Valid @RequestBody OfferDTO offerDTO, Model model){
        offerService.save(offerDTO);
        return "redirect:/main";
    }
}

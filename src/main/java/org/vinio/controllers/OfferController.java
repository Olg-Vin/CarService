package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.OfferDTO;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @GetMapping("/get/{id}")
    public String getOffers(@PathVariable String id, Model model){
        return "offer";
    }

    @PostMapping("/add")
    public String addOffers(@Valid @RequestBody OfferDTO brandDTO, Model model){
        return "offer";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffers(@PathVariable String id, Model model){
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateOffers(@Valid @RequestBody OfferDTO brandDTO, Model model){
        return "redirect:/main";
    }
}

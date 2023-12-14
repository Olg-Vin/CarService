package org.vinio.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.services.imlementations.BrandServiceImpl;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private BrandServiceImpl brandService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/get/{id}")
    public String getBrand(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Show brand with id " + id + " for *principal*");
        model.addAttribute("brands",brandService.getBrand(id));
        return "brand-all";
    }
    @GetMapping("/getAll")
    public String getAllBrands(Model model){
        LOG.log(Level.INFO, "Show all brands for *principal*");
        model.addAttribute("brands",brandService.getAllBrands());
        return "brand-all";
    }

    @PostMapping("/add")
    public String addBrand(@Valid @RequestBody BrandDTO brandDTO, Model model){
        LOG.log(Level.INFO, "*principal* add new brand");
        brandService.add(brandDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* delete brand with id " + id);
        brandService.removeBrand(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateBrand(@Valid @RequestBody BrandDTO brandDTO, Model model){
        LOG.log(Level.INFO, "*principal* update brand with id " + brandDTO.getId());
        brandService.add(brandDTO);
        return "redirect:/main";
    }
}

package org.vinio.controllers;

import jakarta.validation.Valid;
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
    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/get/{id}")
    public String getBrand(@PathVariable String id, Model model){
        model.addAttribute("brand",brandService.get(id));
        return "brand";
    }
    @GetMapping("/getAll")
    public String getAllBrands(Model model){
        model.addAttribute("brand",brandService.getAll());
        return "brand";
    }

    @PostMapping("/add")
    public String addBrand(@Valid @RequestBody BrandDTO brandDTO, Model model){
        brandService.save(brandDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable String id, Model model){
        brandService.delete(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateBrand(@Valid @RequestBody BrandDTO brandDTO, Model model){
        brandService.save(brandDTO);
        return "redirect:/main";
    }
}

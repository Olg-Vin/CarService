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
import org.vinio.dtos.BrandDTO;
import org.vinio.services.imlementations.BrandServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private BrandServiceImpl brandService;
    private static final Logger LOG
            = LogManager.getLogger(Controller.class);
    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }
    @GetMapping("/get/{id}")
    public String getBrand(@PathVariable String id, Model model, Principal principal){
        LOG.log(Level.INFO, principal==null ?
                "Show brand with id " + id :
                "Show brand with id " + id + " for user with name " + principal.getName());
        model.addAttribute("brands",brandService.getBrand(id));
        return "brand-all";
    }
    @GetMapping("/getAll")
    public String getAllBrands(Model model, Principal principal){
        LOG.log(Level.INFO, principal == null ?
                "Show all brands" : "User with name " +
                principal.getName() + " get info for all brand");
        model.addAttribute("brands",brandService.getAllBrands());
        return "brand-all";
    }
    @GetMapping("/add")
    public String addBrand(Model model, Principal principal){
        LOG.log(Level.INFO, principal.getName() + " call page-add for brand");
        model.addAttribute("object", "brand");
        return "page-add";
    }
    @ModelAttribute("brandDTO")
    public BrandDTO initBrand() {
        return new BrandDTO();
    }
    @PostMapping("/add")
    public String addBrand(@Valid BrandDTO brandDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal){
        LOG.log(Level.INFO, principal.getName() + " tried to add new brand");
        if (bindingResult.hasErrors()) {
            LOG.log(Level.INFO, principal.getName() + " has unsuccessful attempt to add brand");
            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.brandDTO",
                    bindingResult);
            return "redirect:/brands/add";
        }
        LOG.log(Level.INFO, principal.getName() + " add new brand");
        brandService.addBrand(brandDTO);
        return "redirect:/brands/getAll";
    }


    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable String id, Model model, Principal principal){
        LOG.log(Level.INFO, principal.getName() + " delete brand with id " + id);
        brandService.removeBrand(id);
        return "redirect:/main";
    }

    @GetMapping("/update/{id}")
    public String updateBrand(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* update brand with id " + id);
        model.addAttribute("object", "brand");
        model.addAttribute("brandDTO", brandService.getBrand(id));
        return "redirect:/main";
    }
}

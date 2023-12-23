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
import org.vinio.dtos.ModelDTO;
import org.vinio.models.enums.Category;
import org.vinio.services.imlementations.BrandServiceImpl;
import org.vinio.services.imlementations.ModelServiceImpl;
import org.vinio.services.imlementations.OfferServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelServiceImpl modelService;
    private BrandServiceImpl brandService;
    private OfferServiceImpl offerService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    public void setModelService(ModelServiceImpl modelService,
                                BrandServiceImpl brandService,
                                OfferServiceImpl offerService) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.offerService = offerService;
    }

    @GetMapping("/get/{id}")
    public String getModel(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* get model with id " + id);
        model.addAttribute("model",modelService.getModel(id));
        model.addAttribute("midlPrice", offerService.getMidlPrice());
        model.addAttribute("offers", offerService.findOfferByModelId(id));
        return "/cards/model-card";
    }
    @GetMapping("/getAll")
    public String getAllModels(Model model, Principal principal){
        LOG.log(Level.INFO, principal == null ?
                "Show all models" : "User with name " +
                principal.getName() + " get info for all models");
        model.addAttribute("models",modelService.getAllModels());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("category", Category.values());
        return "model-all";
    }

    @GetMapping("/getModelsByBrand/{id}")
    public String getModelsByBrand(Model model, @PathVariable String id){
        LOG.log(Level.INFO, "*principal* get all models by brand");
        model.addAttribute("models",modelService.getModelsByBrandId(id));
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("category", Category.values());
        return "model-all";
    }

    @GetMapping("/getModelsByCategory/{category}")
    public String getModelsByCategory(Model model, @PathVariable String category){
        LOG.log(Level.INFO, "*principal* get all models by category");
        model.addAttribute("models",modelService.getModelsByCategory(Category.valueOf(category)));
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("category", Category.values());
        return "model-all";
    }

    @GetMapping("/add")
    public String addModel(Model model){
        LOG.log(Level.INFO, "*principal* call page-add for model");
        model.addAttribute("object", "model");
        return "page-add";
    }
    @ModelAttribute("modelDTO")
    public ModelDTO initModel() {
        return new ModelDTO();
    }
    @PostMapping("/add")
    public String addModel(@Valid ModelDTO modelDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        LOG.log(Level.INFO, "*principal* tried to add new model");
        if (bindingResult.hasErrors()) {
            LOG.log(Level.INFO, "*principal* has unsuccessful attempt to add model");
            redirectAttributes.addFlashAttribute("modelDTO", modelDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelDTO",
                    bindingResult);
            return "redirect:/models/add";
        }
        LOG.log(Level.INFO, "*principal* add new model");
        modelService.add(modelDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteModel(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* delete brand with id "+id);
        modelService.removeModel(id);
        return "redirect:/main";
    }

    @GetMapping("/update/{id}")
    public String updateModel(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "*principal* tried to update model with id "+id);
        model.addAttribute("object", "brand");
        model.addAttribute("modelDTO", modelService.getModel(id));
        return "page-add";
    }
}

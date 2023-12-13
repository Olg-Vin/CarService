package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.ModelDTO;
import org.vinio.services.imlementations.ModelServiceImpl;

@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelServiceImpl modelService;
    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/get/{id}")
    public String getModel(@PathVariable String id, Model model){
        model.addAttribute("models",modelService.getModel(id));
        return "model-all";
    }
    @GetMapping("/getAll")
    public String getAllModels(Model model){
        model.addAttribute("models",modelService.getAllModels());
        return "model-all";
    }

    @PostMapping("/add")
    public String addModel(@Valid @RequestBody ModelDTO modelDTO, Model model){
        modelService.add(modelDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteModel(@PathVariable String id, Model model){
        modelService.removeModel(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateModel(@Valid @RequestBody ModelDTO modelDTO, Model model){
        modelService.add(modelDTO);
        return "redirect:/main";
    }
}

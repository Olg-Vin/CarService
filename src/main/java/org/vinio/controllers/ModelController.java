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
        model.addAttribute("model",modelService.get(id));
        return "model";
    }
    @GetMapping("/getAll")
    public String getAllModels(Model model){
        model.addAttribute("model",modelService.getAll());
        return "model";
    }

    @PostMapping("/add")
    public String addModel(@Valid @RequestBody ModelDTO modelDTO, Model model){
        modelService.save(modelDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteModel(@PathVariable String id, Model model){
        modelService.delete(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateModel(@Valid @RequestBody ModelDTO modelDTO, Model model){
        modelService.save(modelDTO);
        return "redirect:/main";
    }
}

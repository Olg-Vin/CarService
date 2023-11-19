package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.ModelDTO;
import org.vinio.services.imlementations.ModelServiceImpl;

@Controller
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelServiceImpl modelService;

    @GetMapping("/get/{id}")
    public String getModel(@PathVariable String id, Model model){

        return "model";
    }

    @PostMapping("/add")
    public String addModel(@Valid @RequestBody ModelDTO brandDTO, Model model){
        return "model";
    }

    @GetMapping("/delete/{id}")
    public String deleteModel(@PathVariable String id, Model model){
        modelService.delete(id);
        return "main";
    }

    @PostMapping("/update")
    public String updateModel(@Valid @RequestBody ModelDTO brandDTO, Model model){
        return "redirect:/main";
    }
}

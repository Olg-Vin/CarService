package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.UserDTO;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model){
        return "user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO brandDTO, Model model){
        return "user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model){
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @RequestBody UserDTO brandDTO, Model model){
        return "redirect:/main";
    }
}

package org.vinio.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.BrandDTO;
import org.vinio.dtos.UserDTO;
import org.vinio.services.imlementations.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("user",userService.get(id));
        return "user";
    }
    @GetMapping("/getAll")
    public String getAllBrands(Model model){
        model.addAttribute("user",userService.getAll());
        return "user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO, Model model) {
        userService.save(userDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        userService.delete(id);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @RequestBody UserDTO userDTO, Model model) {
        userService.save(userDTO);
        return "redirect:/main";
    }
}

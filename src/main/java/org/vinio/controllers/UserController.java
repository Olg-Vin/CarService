package org.vinio.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vinio.dtos.UserDTO;
import org.vinio.services.imlementations.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model) {
        LOG.log(Level.INFO, "*principal* get user with id " + id);
        model.addAttribute("users",userService.get(id));
        return "user-all";
    }
    @GetMapping("/getAll")
    public String getAllUsers(Model model){
        LOG.log(Level.INFO, "*principal* get all users");
        model.addAttribute("users",userService.getAllUsers());
        return "user-all";
    }

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO, Model model) {
        LOG.log(Level.INFO, "*principal* call page-add for user");
        userService.add(userDTO);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        LOG.log(Level.INFO, "*principal* delete user with id "+id);
        userService.removeUser(id);
        return "redirect:/main";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable String id) {
        LOG.log(Level.INFO, "*principal* tried to update user with id " + id);
//        userService.add(userDTO);
        return "redirect:/main";
    }
}

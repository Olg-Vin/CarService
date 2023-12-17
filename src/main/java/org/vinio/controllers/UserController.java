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
import org.vinio.dtos.UserDTO;
import org.vinio.dtos.UserRegisterDTO;
import org.vinio.services.imlementations.AuthService;
import org.vinio.services.imlementations.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;
    private AuthService authService;
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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("object", "register");
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "auth";
    }
    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initForm() {
        return new UserRegisterDTO();
    }
    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegisterDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable String id, Model model) {
//        LOG.log(Level.INFO, "*principal* delete user with id "+id);
//        userService.removeUser(id);
//        return "redirect:/main";
//    }
//
//    @GetMapping("/update/{id}")
//    public String updateUser(Model model, @PathVariable String id) {
//        LOG.log(Level.INFO, "*principal* tried to update user with id " + id);
//        return "redirect:/main";
//    }
}

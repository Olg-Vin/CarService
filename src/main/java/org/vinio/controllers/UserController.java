package org.vinio.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vinio.dtos.UserDTO;
import org.vinio.dtos.UserProfileDTO;
import org.vinio.dtos.UserRegisterDTO;
import org.vinio.dtos.UserRoleDTO;
import org.vinio.models.User;
import org.vinio.services.imlementations.AuthService;
import org.vinio.services.imlementations.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;
    private AuthService authService;
    private ModelMapper modelMapper;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model) {
        LOG.log(Level.INFO, "*principal* get user with id " + id);
        model.addAttribute("users", userService.get(id));
        return "user-all";
    }

    @GetMapping("/getAll")
    public String getAllUsers(Model model) {
        LOG.log(Level.INFO, "*principal* get all users");
        model.addAttribute("users", userService.getAllUsers());
        return "user-all";
    }

    @GetMapping("/register")
    public String register() {
        LOG.log(Level.INFO, "Open register-page");
        return "registration";
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initForm() {
        return new UserRegisterDTO();
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        LOG.log(Level.INFO, "Open register-page after fail");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
                    bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegisterDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        LOG.log(Level.INFO, "Open login-page");
        return "login";
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
    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        LOG.log(Level.INFO, "Open login-page after fail");

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        LOG.log(Level.INFO, "User with name " + username + " get profile info");
        User user = authService.getUser(username);

        UserProfileDTO userProfileDTO = new UserProfileDTO(
                username,
                user.getFirstName(),
                user.getLastName(),
                modelMapper.map(user.getRole(), UserRoleDTO.class),
                user.getImageUrl()
        );

        model.addAttribute("userDTO", userProfileDTO);

        return "profile";
    }
}

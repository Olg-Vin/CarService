package org.vinio.services.imlementations;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vinio.dtos.UserRegisterDTO;
import org.vinio.models.User;
import org.vinio.models.enums.Role;
import org.vinio.repositories.UserRepository;
import org.vinio.repositories.UserRoleRepository;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;


    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegisterDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getRepeatPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByUsername(registrationDTO.getUsername());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.findRoleByRole(Role.User).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName()
        );

        user.setRole(userRole);

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}

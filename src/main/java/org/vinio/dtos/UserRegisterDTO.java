package org.vinio.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class UserRegisterDTO {
    private String username; // Проверка на уникальность
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }
    @NotEmpty(message = "Repeat password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getRepeatPassword() {
        return repeatPassword;
    }

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFirstName() {
        return firstName;
    }
    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getLastName() {
        return lastName;
    }
}

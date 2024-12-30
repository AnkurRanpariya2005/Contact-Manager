package com.scm.scm.forms;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Min 3 Characters is required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    @UniqueElements(message = "Email already exists")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    private String phone;
    
}

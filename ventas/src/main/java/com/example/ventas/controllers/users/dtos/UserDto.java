package com.example.ventas.controllers.users.dtos;

import com.example.ventas.models.users.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre debe tener máximo 100 caracteres")
    private String name;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String email;

    @NotEmpty(message = "El password no puede estar vacío")
    private String password;

    private String wishlist;

    public User castToUser(){
        return new User(getId(), getName(), getEmail(), getPassword(), getWishlist(), null);
    }
}

package com.example.ventas.controllers.products.dtos;

import com.example.ventas.models.products.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    public ProductDto() {
        this.status = true;
    }
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre debe tener máximo 100 caracteres")
    private String name;

    @NotEmpty(message = "La categoría no puede estar vacía")
    private String category;

    @NotEmpty(message = "El precio no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0.0")
    private String price;

    @NotEmpty(message = "El estado no puede estar vacío")
    private Boolean status;

    public Product castToProduct(){
        return new Product(getId(), getName(), getCategory(), getPrice(), getStatus(), null);
    }
}

package com.example.ventas.controllers.products;

import com.example.ventas.controllers.products.dtos.ProductDto;
import com.example.ventas.models.products.Product;
import com.example.ventas.services.products.ProductService;
import com.example.ventas.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"*"})
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Product>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Product>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Product>> insert(@Valid @RequestBody ProductDto product) {
        return new ResponseEntity<>(this.service.insert(product.castToProduct()), HttpStatus.CREATED);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Product>> update(@Valid @PathVariable long id, @RequestBody ProductDto product) {
        product.setId(id);
        return new ResponseEntity<>(this.service.update(product.castToProduct()), HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(@PathVariable Long id, @RequestBody ProductDto product) {
        product.setId(id);
        return new ResponseEntity<>(this.service.changeStatus(product.castToProduct()), HttpStatus.OK);
    }
}

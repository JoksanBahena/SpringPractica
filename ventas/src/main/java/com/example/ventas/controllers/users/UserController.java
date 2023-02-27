package com.example.ventas.controllers.users;

import com.example.ventas.controllers.users.dtos.UserDto;
import com.example.ventas.models.users.User;
import com.example.ventas.services.users.UserService;
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
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(this.service.insert(user.castToUser()), HttpStatus.CREATED);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<User>> update(@Valid @PathVariable long id, @RequestBody UserDto user) {
        user.setId(id);
        return new ResponseEntity<>(this.service.update(user.castToUser()), HttpStatus.OK);
    }
}

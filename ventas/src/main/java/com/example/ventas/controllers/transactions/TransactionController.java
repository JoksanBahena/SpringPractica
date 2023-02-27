package com.example.ventas.controllers.transactions;

import com.example.ventas.controllers.transactions.dtos.TransactionDto;
import com.example.ventas.models.transactions.Transaction;
import com.example.ventas.services.transactions.TransactionService;
import com.example.ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"*"})
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Transaction>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Transaction>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Transaction>> insert(@RequestBody TransactionDto transaction) {
        return new ResponseEntity<>(this.service.insert(transaction.castToTransaction()), HttpStatus.CREATED);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Transaction>> update(@PathVariable long id, @RequestBody TransactionDto transaction) {
        transaction.setId(id);
        return new ResponseEntity<>(this.service.update(transaction.castToTransaction()), HttpStatus.OK);
    }
}

package com.example.ventas.controllers.transactions.dtos;

import com.example.ventas.models.products.Product;
import com.example.ventas.models.transactions.Transaction;
import com.example.ventas.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private Long id;
    private User user;
    private Product product;

    public Transaction castToTransaction() {
        return new Transaction(getId(), getUser(), getProduct(), null);
    }
}

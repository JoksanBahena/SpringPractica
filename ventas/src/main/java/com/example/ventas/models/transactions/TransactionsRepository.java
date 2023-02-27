package com.example.ventas.models.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);
    Optional<Transaction> findByUser(Long id);

    boolean existsById(Long id);
}

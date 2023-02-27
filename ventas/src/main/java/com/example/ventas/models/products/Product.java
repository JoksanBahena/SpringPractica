package com.example.ventas.models.products;

import com.example.ventas.models.transactions.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "category", nullable = false, length = 255)
    private String category;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1", insertable = false)
    private Boolean status;

    @OneToMany(mappedBy = "product")
    private Set<Transaction> transactions;
}

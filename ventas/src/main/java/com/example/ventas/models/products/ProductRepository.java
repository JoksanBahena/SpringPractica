package com.example.ventas.models.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByCategory(String category);

    @Modifying
    @Query(
            value = "UPDATE products SET status = :status WHERE id = :id",nativeQuery = true
    )
    int updateStatusById(@Param("status") boolean status,@Param("id") Long id);
}

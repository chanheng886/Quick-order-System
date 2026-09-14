package com.example.backend.repositories;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);   
}
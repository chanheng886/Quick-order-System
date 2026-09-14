package com.example.backend.entities;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table (name = "tb_product")
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Getter 
@Setter 
public class Product {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private BigDecimal price;
   
    @Column (name = "image_url")
    private String imageUrl;

    @Column (name = "is_available", nullable = false)
    private boolean isAvailable = true;
}
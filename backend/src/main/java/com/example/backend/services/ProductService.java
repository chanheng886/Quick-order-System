package com.example.backend.services;
import java.util.List;
import java.util.UUID;
import com.example.backend.dtos.RequestDTO.ProductRequestDTO;
import com.example.backend.dtos.ResponseDTO.ProductResponseDTO;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(UUID id);
    ProductResponseDTO getProductByName(String name);
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto);
    void deleteProduct(UUID id);
}

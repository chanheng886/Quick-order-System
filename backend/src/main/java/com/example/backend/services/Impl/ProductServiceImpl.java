package com.example.backend.services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dtos.RequestDTO.ProductRequestDTO;
import com.example.backend.dtos.ResponseDTO.ProductResponseDTO;
import com.example.backend.entities.Product;
import com.example.backend.mappers.ProductMapper;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.services.ProductService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional 
    @Override 
    public List<ProductResponseDTO> getAllProducts(){
        return productRepository.findAll()
            .stream()
            .map(productMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional 
    @Override 
    public ProductResponseDTO getProductById(UUID id){
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found!"));
        return  productMapper.toResponse(product);
    }

    @Transactional 
    @Override 
    public ProductResponseDTO getProductByName(String name){
        Product product = productRepository
            .findByName(name).orElseThrow(() -> new RuntimeException("Product with name: " + name + " not found!"));
        return productMapper.toResponse(product);
    }

    @Transactional 
    @Override 
    public ProductResponseDTO createProduct(ProductRequestDTO dto){
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Transactional 
    @Override 
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found!"));
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setImageUrl(dto.imageUrl());
        product.setPrice(dto.price());
        Product update = productRepository.save(product);
        return productMapper.toResponse(update);
    }

    @Transactional 
    @Override 
    public void deleteProduct(UUID id){
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found!"));
        productRepository.delete(product);
    }
}

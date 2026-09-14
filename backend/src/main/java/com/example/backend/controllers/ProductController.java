package com.example.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.RequestDTO.ProductRequestDTO;
import com.example.backend.dtos.ResponseDTO.ProductResponseDTO;
import com.example.backend.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/v1/product")
@RequiredArgsConstructor 
public class ProductController {
    private final ProductService productService;

    @GetMapping 
    public List<ProductResponseDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}") 
    public ProductResponseDTO getProductByid(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @GetMapping ("/search/{name}")
    public ProductResponseDTO getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    @PostMapping 
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO dto){
        return productService.createProduct(dto);
    }

    @PutMapping ("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable UUID id, @RequestParam ProductRequestDTO dto){
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping 
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}

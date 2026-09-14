package com.example.backend.mappers;
import org.springframework.stereotype.Component;
import com.example.backend.dtos.RequestDTO.ProductRequestDTO;
import com.example.backend.dtos.ResponseDTO.ProductResponseDTO;
import com.example.backend.entities.Product;

@Component 
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto){
        if(dto==null){
            return null;
        }
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setAvailable(dto.isAvailable());
        return product;
    }
    public ProductResponseDTO toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImageUrl(),
            product.isAvailable()
        );
    }
}

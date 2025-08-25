package com.example.crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudapp.model.Product;
import com.example.crudapp.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> searchProductsByName(String productName) {
        return productRepository.findByNameContainingIgnoreCase(productName);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            product.setStockQuantity(productDetails.getStockQuantity());
            product.setCategory(productDetails.getCategory());
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Long getProductCount() {
        return productRepository.count();
    }

}

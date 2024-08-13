package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductByProductId(int productId) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of();
    }

    @Override
    public List<Product> getAllTrue() {
        return List.of();
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        return null;
    }

    @Override
    public boolean updateStatus(int productId) {
        return false;
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }
}

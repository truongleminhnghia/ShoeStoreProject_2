package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductByProductId(int productId);
    List<Product> getAllProduct();
    List<Product> getAllTrue();
    Product updateProduct(int productId, Product product);
    boolean updateStatus(int productId);
    Product getProductByName(String productName);
}

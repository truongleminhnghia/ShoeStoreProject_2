package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.requests.SizeRequest;
import org.project.shoestoreproject.dto.respones.ProductRespone;
import org.project.shoestoreproject.entitíes.Category;
import org.project.shoestoreproject.entitíes.Product;

import java.util.List;

public interface ProductService {
    public boolean createProduct(Product product);
    Product getProductByProductId(int productId);
    List<Product> getAllProduct();
    List<Product> getAllTrue();
    List<Product> getAllFasle();
    Product updateProduct(int productId, Product product);
    int changeStatusFasle(int productId);
    List<Product> getProductByName(String productName);
    List<Product> getByCategory(String categoryName);
    Category getCateByName(String categoryName);
    int getQuantity(List<SizeRequest> sizeRequests);
    ProductRespone convertToProductRespone(Product product);
}

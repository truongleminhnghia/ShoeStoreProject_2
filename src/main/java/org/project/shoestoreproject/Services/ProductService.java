package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.DTO.Request.SizeRequest;
import org.project.shoestoreproject.DTO.Respone.ProductRespone;
import org.project.shoestoreproject.Entity.Category;
import org.project.shoestoreproject.Entity.Product;

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

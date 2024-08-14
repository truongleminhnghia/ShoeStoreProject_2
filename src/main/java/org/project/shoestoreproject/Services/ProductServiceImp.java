package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.DTO.Request.SizeRequest;
import org.project.shoestoreproject.DTO.Respone.ProductRespone;
import org.project.shoestoreproject.DTO.Respone.SizeRespone;
import org.project.shoestoreproject.Entity.Category;
import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryService categoryService;
    @Autowired private SizeService sizeService;

    @Override
    public boolean createProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return false;
        }
    }

    @Override
    public Product getProductByProductId(int productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllTrue() {
        return productRepository.findAllTrue();
    }

    @Override
    public List<Product> getAllFasle() {
        return productRepository.findAllFasle();
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        Product productExisting = getProductByProductId(productId);
        if(productExisting == null) throw  new RuntimeException("Product not exist");
        return productRepository.save(productExisting);
    }

    @Override
    @Transactional
    public int changeStatusFasle(int productId) {
        return productRepository.updateStatus(productId);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productRepository.findAllName(productName);
    }

    @Override
    public List<Product> getByCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }

    @Override
    public Category getCateByName(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return category;
    }

    @Override
    public int getQuantity(List<SizeRequest> sizeRequests) {
        int quantity = 0;
        for (SizeRequest sizeRequest : sizeRequests) {
            quantity += sizeRequest.getQuantity();
        }
        return quantity;
    }

    @Override
    public ProductRespone convertToProductRespone(Product product) {
        ProductRespone productRespone = new ProductRespone();
        productRespone.setProductId(product.getProductId());
        productRespone.setProductName(product.getProductName());
        productRespone.setColor(product.getColor());
        productRespone.setBrand(product.getBrand());
        productRespone.setPrice(product.getPrice());
        productRespone.setQuantity(product.getStockQuantity());
        productRespone.setDescription(product.getDescription());
        productRespone.setActive(product.isActive());
        productRespone.setCategory(product.getCategory().getCategoryName());
        List<SizeRespone> sizeRespones = sizeService.convertToRespone(product.getSizes());
        productRespone.setSizes(sizeRespones);
        return productRespone;
    }
}

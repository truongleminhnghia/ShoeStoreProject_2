package org.project.shoestoreproject.Controller;

import org.project.shoestoreproject.DTO.Request.CreationProductRequest;
import org.project.shoestoreproject.DTO.Request.SizeRequest;
import org.project.shoestoreproject.DTO.Request.UpdateProductRequest;
import org.project.shoestoreproject.DTO.Respone.ObjectRespone;
import org.project.shoestoreproject.DTO.Respone.ProductRespone;
import org.project.shoestoreproject.Entity.Category;
import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Entity.Size;
import org.project.shoestoreproject.Services.CategoryService;
import org.project.shoestoreproject.Services.ImageService;
import org.project.shoestoreproject.Services.ProductService;
import org.project.shoestoreproject.Services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shoeStoreProject/products")
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private CategoryService categoryService;
    @Autowired private SizeService sizeService;
    @Autowired private ImageService imageService;

    @PostMapping("/create_product")
    public ResponseEntity<ObjectRespone> createProduct(@RequestBody CreationProductRequest creationProductRequest) {
        try {
            Product newProduct = new Product();
            newProduct.setProductName(creationProductRequest.getProductName());
            newProduct.setDescription(creationProductRequest.getDescription());
            newProduct.setPrice(creationProductRequest.getPrice());
            newProduct.setColor(creationProductRequest.getColor());
            newProduct.setBrand(creationProductRequest.getBrand());
            newProduct.setStockQuantity(productService.getQuantity(creationProductRequest.getSizes()));
            Category cateExists = productService.getCateByName(creationProductRequest.getCategoryName());
            if (cateExists == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ObjectRespone("Failed", "Category not exist", null));
            }
            newProduct.setCategory(cateExists);
            boolean createProduct = productService.createProduct(newProduct);
            if (!createProduct) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ObjectRespone("Failed", "Product creation failed", null));
            }
            List<Size> sizes = new ArrayList<>();
            for (SizeRequest sizeRequest : creationProductRequest.getSizes()) {
                Size size = new Size();
                size.setSizeValue(sizeRequest.getSizeValue());
                size.setQuantity(sizeRequest.getQuantity());
                size.setProduct(newProduct);
                sizeService.saveSize(size);
                sizes.add(size);
            }
            newProduct.setSizes(sizes);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ObjectRespone("Success", "Product created successfully", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @GetMapping("/get_all")
    public ResponseEntity<ObjectRespone> getAllProducts() {
        try {
            List<Product> list = productService.getAllProduct();
            if(list.isEmpty() || list == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new  ObjectRespone("Failed", "Product list is empty", null));
            List<ProductRespone> productResponeList = new ArrayList<>();
            for (Product product : list) {
                ProductRespone productRespone = productService.convertToProductRespone(product);
                productResponeList.add(productRespone);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Product list ", productResponeList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @GetMapping("/product/{product_Id}")
    public ResponseEntity<ObjectRespone> getProductById(@PathVariable("product_Id") int productId) {
        try {
            Product productExisting = productService.getProductByProductId(productId);
            if(productExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new  ObjectRespone("Failed", "Product not exist", null));
            ProductRespone productRespone = productService.convertToProductRespone(productExisting);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Product with ID: " + productId, productRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @GetMapping("/get_all_true")
    public ResponseEntity<ObjectRespone> getAllTrue() {
        try {
            List<Product> list = productService.getAllTrue();
            if(list == null || list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new  ObjectRespone("Failed", "Product list is empty", null));
            }
            List<ProductRespone> productResponeList = new ArrayList<>();
            for (Product product : list) {
                ProductRespone productRespone = productService.convertToProductRespone(product);
                productResponeList.add(productRespone);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Product list ", productResponeList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @GetMapping("/get_all_false")
    public ResponseEntity<ObjectRespone> getAllFalse() {
        try {
            List<Product> list = productService.getAllFasle();
            if(list == null || list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new  ObjectRespone("Failed", "Product list is empty", null));
            }
            List<ProductRespone> productResponeList = new ArrayList<>();
            for (Product product : list) {
                ProductRespone productRespone = productService.convertToProductRespone(product);
                productResponeList.add(productRespone);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Product list ", productResponeList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @GetMapping("/categpry_name/{categpry_name}")
    public ResponseEntity<ObjectRespone> getByCategory(@PathVariable("categpry_name") String categoryName) {
        try {
            List<Product> products = productService.getByCategory(categoryName);
            if(products == null || products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new  ObjectRespone("Failed", "Product list is empty", null));
            }
            List<ProductRespone> productResponeList = new ArrayList<>();
            for (Product product : products) {
                ProductRespone productRespone = productService.convertToProductRespone(product);
                productResponeList.add(productRespone);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Product list ", productResponeList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @PostMapping("/update_product/{product_id}")
    public ResponseEntity<ObjectRespone> updateProduct(
            @PathVariable("product_id") int productId,
            @RequestBody UpdateProductRequest productRequest) {
        try {
            Product productExisting = productService.getProductByProductId(productId);
            if(productExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "product not exist", null));
            if(productRequest.getProductName() != null)
                productExisting.setProductName(productRequest.getProductName());
            if(productRequest.getColor() != null)
                productExisting.setColor(productRequest.getColor());
            if(productRequest.getBand() != null)
                productExisting.setBrand(productRequest.getBand());
            if(productRequest.getPrice() > 0.0)
                productExisting.setPrice(productRequest.getPrice());
            if(productService.getQuantity(productRequest.getSizeRequests()) > 0)
                productExisting.setStockQuantity(productExisting.getStockQuantity()
                        + productService.getQuantity(productRequest.getSizeRequests()));
            if(productRequest.getSizeRequests() != null || !productRequest.getSizeRequests().isEmpty())
                sizeService.update(productExisting.getProductId(), productRequest.getSizeRequests());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new  ObjectRespone("Success", "Update product successfully ", productExisting));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

    @PutMapping("/status/{product_Id}")
    public ResponseEntity<ObjectRespone> changeProductStatus(@PathVariable("product_Id") int productId) {
        try {
            int result = productService.changeStatusFasle(productId);
            if (result > 0)
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new  ObjectRespone("Success", "Update status product successfully ", null));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new  ObjectRespone("Failed", "Update status product failed ", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
        }
    }

}

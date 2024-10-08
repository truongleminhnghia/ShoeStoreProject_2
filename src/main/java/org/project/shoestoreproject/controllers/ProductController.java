//package org.project.shoestoreproject.controllers;
//
//import org.project.shoestoreproject.dto.requests.CreationProductRequest;
//import org.project.shoestoreproject.dto.requests.SizeRequest;
//import org.project.shoestoreproject.dto.requests.UpdateProductRequest;
//import org.project.shoestoreproject.dto.respones.ObjectRespone;
//import org.project.shoestoreproject.dto.respones.ProductRespone;
//import org.project.shoestoreproject.entities.Category;
//import org.project.shoestoreproject.entities.Product;
//import org.project.shoestoreproject.entities.Size;
//import org.project.shoestoreproject.services.CategoryService;
//import org.project.shoestoreproject.services.ImageService;
//import org.project.shoestoreproject.services.ProductService;
//import org.project.shoestoreproject.services.SizeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/shoeStoreProject/products")
//public class ProductController {
//
//    @Autowired private ProductService productService;
//    @Autowired private CategoryService categoryService;
//    @Autowired private SizeService sizeService;
//    @Autowired private ImageService imageService;
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create_product")
//    public ResponseEntity<ObjectRespone> createProduct(@RequestBody CreationProductRequest creationProductRequest) {
//        try {
//            Product newProduct = new Product();
//            newProduct.setProductName(creationProductRequest.getProductName());
//            newProduct.setDescription(creationProductRequest.getDescription());
//            newProduct.setPrice(creationProductRequest.getPrice());
//            newProduct.setColor(creationProductRequest.getColor());
//            newProduct.setBrand(creationProductRequest.getBrand());
//            newProduct.setStockQuantity(productService.getQuantity(creationProductRequest.getSizes()));
//            Category cateExists = productService.getCateByName(creationProductRequest.getCategoryName());
//            if (cateExists == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ObjectRespone("Failed", "Category not exist", null));
//            }
//            newProduct.setCategory(cateExists);
//            boolean createProduct = productService.createProduct(newProduct);
//            if (!createProduct) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(new ObjectRespone("Failed", "Product creation failed", null));
//            }
//            List<Size> sizes = new ArrayList<>();
//            for (SizeRequest sizeRequest : creationProductRequest.getSizes()) {
//                Size size = new Size();
//                size.setSizeValue(sizeRequest.getSizeValue());
//                size.setQuantity(sizeRequest.getQuantity());
//                size.setProduct(newProduct);
//                sizeService.saveSize(size);
//                sizes.add(size);
//            }
//            newProduct.setSizes(sizes);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new ObjectRespone("Success", "Product created successfully", newProduct));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
//        }
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/get_all_false")
//    public ResponseEntity<ObjectRespone> getAllFalse() {
//        try {
//            List<Product> list = productService.getAllFasle();
//            if(list == null || list.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new  ObjectRespone("Failed", "Product list is empty", null));
//            }
//            List<ProductRespone> productResponeList = new ArrayList<>();
//            for (Product product : list) {
//                ProductRespone productRespone = productService.convertToProductRespone(product);
//                productResponeList.add(productRespone);
//            }
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new  ObjectRespone("Success", "Product list ", productResponeList));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
//        }
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/update_product/{product_id}")
//    public ResponseEntity<ObjectRespone> updateProduct(
//            @PathVariable("product_id") int productId,
//            @RequestBody UpdateProductRequest productRequest) {
//        try {
//            Product productExisting = productService.getProductByProductId(productId);
//            if(productExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ObjectRespone("Failed", "product not exist", null));
//            if(productRequest.getProductName() != null)
//                productExisting.setProductName(productRequest.getProductName());
//            if(productRequest.getColor() != null)
//                productExisting.setColor(productRequest.getColor());
//            if(productRequest.getBand() != null)
//                productExisting.setBrand(productRequest.getBand());
//            if(productRequest.getPrice() > 0.0)
//                productExisting.setPrice(productRequest.getPrice());
//            if(productService.getQuantity(productRequest.getSizeRequests()) > 0)
//                productExisting.setStockQuantity(productExisting.getStockQuantity()
//                        + productService.getQuantity(productRequest.getSizeRequests()));
//            if(productRequest.getSizeRequests() != null || !productRequest.getSizeRequests().isEmpty())
//                sizeService.update(productExisting.getProductId(), productRequest.getSizeRequests());
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new  ObjectRespone("Success", "Update product successfully ", productExisting));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
//        }
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/status/{product_Id}")
//    public ResponseEntity<ObjectRespone> changeProductStatus(@PathVariable("product_Id") int productId) {
//        try {
//            int result = productService.changeStatusFasle(productId);
//            if (result > 0)
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(new  ObjectRespone("Success", "Update status product successfully ", null));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new  ObjectRespone("Failed", "Update status product failed ", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ObjectRespone("Failed", "Message: " + e.getMessage(), null));
//        }
//    }
//
//}

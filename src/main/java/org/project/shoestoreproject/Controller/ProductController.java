package org.project.shoestoreproject.Controller;

import org.project.shoestoreproject.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ProductController {
    @Autowired private ProductRepository productRepository;
}

package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
//@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@Data

public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", columnDefinition = "NVARCHAR(300)", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description;

    @Column(name = "brand", columnDefinition = "NVARCHAR(300)")
    private String brand;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Image> images =  new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Size> sizes=  new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductColor> productColor;
}

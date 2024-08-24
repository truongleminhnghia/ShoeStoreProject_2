package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_size")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_size_id")
    private int productSizeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;
}

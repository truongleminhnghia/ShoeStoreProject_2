package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_color")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_color_Id", nullable = false)
    private int productColorId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "colorId", nullable = false)
    private Color color;
}

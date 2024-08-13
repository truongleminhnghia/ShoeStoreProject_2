package org.project.shoestoreproject.Entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@Data

public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name", nullable = false, columnDefinition = "NVARCHAR(250)")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}

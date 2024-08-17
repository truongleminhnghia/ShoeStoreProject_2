package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
//@Table(name = "category")
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

    @Column(name = "is_active")
    private boolean isActive = true;
}

package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.project.shoestoreproject.enums.EnumColorName;

@Entity
@Table(name = "color")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Color extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id", nullable = false)
    private int colorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "color_name", nullable = false)
    private EnumColorName colorName;
}

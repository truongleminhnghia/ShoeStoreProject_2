package org.project.shoestoreproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.project.shoestoreproject.enums.EnumRoleName;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor


public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private EnumRoleName roleName;

    @JsonBackReference
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();
}

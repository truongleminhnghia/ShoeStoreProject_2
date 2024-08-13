package org.project.shoestoreproject.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@Data

public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "first_name", columnDefinition = "NVARCHAR(300)")
    private String fisrtName;

    @Column(name = "last_name", columnDefinition = "NVARCHAR(300)")
    private String lastName;

    @Column(name = "email", columnDefinition = "NVARCHAR(300)")
    private String email;

    @Column(name = "password",nullable = false, columnDefinition = "NVARCHAR(300)")
    private String password;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Column(name = "addess", columnDefinition = "NVARCHAR(300)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks = new ArrayList<>();

    @Transient
    private String roleName;

    public User(String userName, String fisrtName, String lastName, String email, String password,
                String phoneNumber, LocalDate birthDate, String address, Role role, boolean isActive,
                String roleName) {
        this.userName = userName;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
        this.isActive = isActive;
        this.roleName = roleName;
    }
}

package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.project.shoestoreproject.enums.EnumOrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "full_name", nullable = false, columnDefinition = "NVARCHAR(300)")
    private String fullName;

    @Column(name = "phone_shipping", nullable = false)
    private String phoneShipping;

    @Column(name = "address_shipping", nullable = false, columnDefinition = "NVARCHAR(300)")
    private String addressShipping;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "order_date", nullable = false)
    @DateTimeFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private EnumOrderStatus status;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "reason" ,columnDefinition = "NVARCHAR(300)")
    private String reason;

    @Column(name = "note" ,columnDefinition = "NVARCHAR(500)")
    private String note;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();


}

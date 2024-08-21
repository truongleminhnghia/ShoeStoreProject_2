package org.project.shoestoreproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
//@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@Data

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbak_id")
    private int feedbackId;

    @Column(name = "rating")
    private double rating;

    @Column(name = "coment", columnDefinition = "NVARCHAR(500)")
    private String comment;

    @Column(name = "feedback_date")
    private LocalDateTime feedbackDate;

    @Column(name = "í_read")
    private boolean isRead = false;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}

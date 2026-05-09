
package com.mediqueue.mediqueue.model;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "doctors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String qualification;
    private String specialization;
    private int experienceYears;
    private int consultationFee;
    private String bio;
    private boolean available;
}
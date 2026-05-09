package com.mediqueue.mediqueue.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDate appointmentDate;
    private Integer tokenNumber;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String patientNotes;
    private LocalDateTime bookedAt;
    private LocalDateTime consultedAt;

    @PrePersist
    protected void onCreate() {
        bookedAt = LocalDateTime.now();
        status = AppointmentStatus.BOOKED;
    }

    public enum AppointmentStatus {
        BOOKED, WAITING, IN_CONSULTATION,
        CONSULTED, CANCELLED, NO_SHOW
    }
}

package com.infnet.appointmentsapi.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private AppointmentStatus status;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;
}

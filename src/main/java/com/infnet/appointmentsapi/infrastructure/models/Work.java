package com.infnet.appointmentsapi.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false)
    private Duration estimatedTime;

    @Column(nullable = false)
    private BigDecimal price;
}

package com.infnet.appointmentsapi.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Column(nullable = false)
    private boolean available;

    public boolean isAvailable() {
        return available;
    }
}

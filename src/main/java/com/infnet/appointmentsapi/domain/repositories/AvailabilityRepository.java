package com.infnet.appointmentsapi.domain.repositories;

import com.infnet.appointmentsapi.infrastructure.models.Availability;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Availability findByDate(LocalDate date);
}

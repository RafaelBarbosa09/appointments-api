package com.infnet.appointmentsapi.domain.repositories;

import com.infnet.appointmentsapi.infrastructure.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerId(Long customerId);
    List<Appointment> findByProfessionalId(Long professionalId);
    Appointment findByAppointmentDateTime(LocalDateTime appointmentDateTime);
}

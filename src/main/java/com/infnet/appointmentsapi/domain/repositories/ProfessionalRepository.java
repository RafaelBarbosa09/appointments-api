package com.infnet.appointmentsapi.domain.repositories;

import com.infnet.appointmentsapi.infrastructure.models.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Professional getByUserId(Long userId);
}

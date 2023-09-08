package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.ProfessionalRepository;
import com.infnet.appointmentsapi.infrastructure.models.Professional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }
}

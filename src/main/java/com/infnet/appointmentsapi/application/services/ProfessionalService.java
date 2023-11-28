package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.ProfessionalRepository;
import com.infnet.appointmentsapi.domain.repositories.UserRepository;
import com.infnet.appointmentsapi.infrastructure.models.Professional;
import com.infnet.appointmentsapi.infrastructure.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;
    private final UserRepository userRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository, UserRepository userRepository) {
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
    }

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    public Professional createProfessional(Professional professional, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        professional.setUser(user);

        return professionalRepository.save(professional);
    }

    public Professional getPofessionalByUserId(Long userId) {
        Professional professional = professionalRepository.getByUserId(userId);
        if (Objects.isNull(professional)) {
            throw new RuntimeException("Professional not found");
        }

        return professional;
    }
}

package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.WorkRepository;
import com.infnet.appointmentsapi.infrastructure.models.Work;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {
    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }
}

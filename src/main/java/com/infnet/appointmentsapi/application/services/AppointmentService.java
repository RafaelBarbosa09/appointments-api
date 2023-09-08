package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.*;
import com.infnet.appointmentsapi.infrastructure.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ProfessionalRepository professionalRepository;
    private final WorkRepository workRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              AddressRepository addressRepository,
                              ProfessionalRepository professionalRepository,
                              WorkRepository workRepository,
                              AppointmentStatusRepository appointmentStatusRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.professionalRepository = professionalRepository;
        this.workRepository = workRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
    }

    public Appointment create(Appointment appointment) {
        Customer customer = appointment.getCustomer();
        if (customer.getId() != null) {
            customer = customerRepository.findById(appointment.getCustomer().getId()).orElse(null);
        }
        appointment.setCustomer(customer);

        Address address = appointment.getCustomer().getAddress();
        if (address.getId() != null) {
            address = addressRepository.findById(appointment.getCustomer().getAddress().getId()).orElse(null);
        }
        appointment.getCustomer().setAddress(address);

        Professional professional = appointment.getProfessional();
        if (professional.getId() != null) {
            professional = professionalRepository.findById(appointment.getProfessional().getId()).orElse(null);
        }
        appointment.setProfessional(professional);

        Work work = appointment.getWork();
        if (work.getId() != null) {
            work = workRepository.findById(appointment.getWork().getId()).orElse(null);
        }
        appointment.setWork(work);

        long statusScheduledId = 1L;
        AppointmentStatus status = appointmentStatusRepository.findById(statusScheduledId).orElse(null);
        appointment.setStatus(status);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

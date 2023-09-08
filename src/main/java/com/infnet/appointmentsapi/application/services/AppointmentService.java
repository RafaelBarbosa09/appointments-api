package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.*;
import com.infnet.appointmentsapi.infrastructure.models.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ProfessionalRepository professionalRepository;
    private final WorkRepository workRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              AddressRepository addressRepository,
                              ProfessionalRepository professionalRepository,
                              WorkRepository workRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.professionalRepository = professionalRepository;
        this.workRepository = workRepository;
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

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

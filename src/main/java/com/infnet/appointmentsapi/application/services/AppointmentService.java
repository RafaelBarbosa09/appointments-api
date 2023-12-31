package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.*;
import com.infnet.appointmentsapi.infrastructure.models.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ProfessionalRepository professionalRepository;
    private final WorkRepository workRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final TimeSlotRepository timeSlotRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            ProfessionalRepository professionalRepository,
            WorkRepository workRepository,
            AppointmentStatusRepository appointmentStatusRepository,
            TimeSlotRepository timeSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.professionalRepository = professionalRepository;
        this.workRepository = workRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.timeSlotRepository = timeSlotRepository;
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
        appointment.setAppointmentDateTime(appointment.getAppointmentDateTime().minusHours(3));

        Appointment dupplicatedAppointment = appointmentRepository
                .findByAppointmentDateTime(appointment.getAppointmentDateTime());
        if (Objects.nonNull(dupplicatedAppointment)) {
            throw new RuntimeException("Appointment already exists");
        }

        Appointment appointmentCreated = appointmentRepository.save(appointment);

        appointmentCreated.getProfessional().getAvailability().forEach(availability -> {
            if (availability.getDate().equals(appointmentCreated.getAppointmentDateTime().toLocalDate())) {
                availability.getTimeSlots().forEach(timeSlot -> {
                    LocalTime timeSlotStartTime = timeSlot.getStartTime().truncatedTo(ChronoUnit.MINUTES);
                    LocalTime appointmentTime = appointmentCreated.getAppointmentDateTime().toLocalTime()
                            .truncatedTo(ChronoUnit.MINUTES);
                    if (appointmentTime.equals(timeSlotStartTime)) {
                        timeSlot.setAvailable(false);
                        timeSlotRepository.save(timeSlot);
                    }
                });
            }
        });

        return appointmentCreated;
    }

    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();
        // format date to datetime-local
        // dont return datetime array. return datetime object
        // appointments.forEach(appointment -> {
        // appointment.setAppointmentDateTime((LocalDateT)
        // appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        // });
        return appointments;
    }

    public List<Appointment> getAppointmentsByCustomerId(Long customerId) {
        List<Appointment> appointments = appointmentRepository.findByCustomerId(customerId);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        return appointments = appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDateTime))
                .filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().isAfter(yesterday))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByProfessionalId(Long professionalId) {
        List<Appointment> appointments = appointmentRepository.findByProfessionalId(professionalId);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        return appointments = appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDateTime))
                .filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().isAfter(yesterday))
                .collect(Collectors.toList());
    }

    public Appointment updateStatus(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (Objects.isNull(appointment)) {
            throw new RuntimeException("Appointment not found");
        }

        long statusCanceledId = 2L;
        AppointmentStatus status = appointmentStatusRepository.findById(statusCanceledId).orElse(null);
        appointment.setStatus(status);

        return appointmentRepository.save(appointment);
    }
}

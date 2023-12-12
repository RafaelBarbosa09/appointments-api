package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.*;

import static org.mockito.Mockito.*;

import com.infnet.appointmentsapi.infrastructure.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ProfessionalRepository professionalRepository;

    @Mock
    private WorkRepository workRepository;

    @Mock
    private AppointmentStatusRepository appointmentStatusRepository;

    @InjectMocks
    private AppointmentService appointmentService;


    @Test
    void create_Success() {
        Customer customer = new Customer();
        customer.setId(1L);

        Address address = new Address();
        address.setId(1L);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(LocalTime.now().truncatedTo(ChronoUnit.HOURS));
        timeSlot.setEndTime(LocalTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1));

        Availability availability = new Availability();
        availability.setDate(LocalDate.now());
        availability.setTimeSlots(List.of(timeSlot));

        Professional professional = new Professional();
        professional.setId(1L);
        professional.setAvailability(List.of(availability));

        Work work = new Work();
        work.setId(1L);

        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(LocalDateTime.now());
        appointment.setCustomer(customer);
        customer.setAddress(address);
        appointment.setProfessional(professional);
        appointment.setWork(work);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));
        when(workRepository.findById(1L)).thenReturn(Optional.of(work));
        when(appointmentStatusRepository.findById(1L)).thenReturn(Optional.of(new AppointmentStatus()));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.create(appointment);

        verify(customerRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
        verify(professionalRepository, times(1)).findById(1L);
        verify(workRepository, times(1)).findById(1L);
        verify(appointmentStatusRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(appointment);

        assertEquals(appointment, createdAppointment);
    }

    @Test
    void getAllAppointments() {
        when(appointmentRepository.findAll()).thenReturn(List.of(new Appointment()));

        assertDoesNotThrow(() -> appointmentService.getAllAppointments());

        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void getAppointmentsByCustomerId() {
        when(appointmentRepository.findByCustomerId(1L)).thenReturn(List.of(new Appointment()));

        assertDoesNotThrow(() -> appointmentService.getAppointmentsByCustomerId(1L));

        verify(appointmentRepository, times(1)).findByCustomerId(1L);
    }

    @Test
    void getAppointmentsByProfessionalId() {
        when(appointmentRepository.findByProfessionalId(1L)).thenReturn(List.of(new Appointment()));

        assertDoesNotThrow(() -> appointmentService.getAppointmentsByProfessionalId(1L));

        verify(appointmentRepository, times(1)).findByProfessionalId(1L);
    }

    @Test
    void updateStatus() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        assertDoesNotThrow(() -> appointmentService.updateStatus(1L));

        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void updateStatus_ShouldThrowNotFound() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> appointmentService.updateStatus(1L));

        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(0)).save(any(Appointment.class));
    }
}
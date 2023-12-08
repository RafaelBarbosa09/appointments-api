package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.AddressRepository;
import com.infnet.appointmentsapi.domain.repositories.CustomerRepository;
import com.infnet.appointmentsapi.domain.repositories.UserRepository;
import static org.mockito.Mockito.*;

import com.infnet.appointmentsapi.infrastructure.models.Address;
import com.infnet.appointmentsapi.infrastructure.models.Customer;
import com.infnet.appointmentsapi.infrastructure.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomer() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Address address = new Address();
        address.setId(1L);

        Customer customer = new Customer();
        customer.setAddress(address);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(addressRepository.findById(1L)).thenReturn(java.util.Optional.of(address));
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer, userId);

        verify(userRepository, times(1)).findById(userId);
        verify(addressRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);

        assertEquals(customer, createdCustomer);
        assertEquals(user, createdCustomer.getUser());
    }

    @Test
    void getCustomerByUserId() {
        when(customerRepository.getByUserId(1L)).thenReturn(new Customer());

        assertDoesNotThrow(() -> customerService.getCustomerByUserId(1L));

        verify(customerRepository, times(1)).getByUserId(1L);
    }

    @Test
    void getCustomerByUserId_ThrowsException() {
        when(customerRepository.getByUserId(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> customerService.getCustomerByUserId(1L));

        verify(customerRepository, times(1)).getByUserId(1L);
    }

    @Test
    void getCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(new Customer()));

        assertDoesNotThrow(() -> customerService.getCustomerById(1L));

        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getCustomerById_ThrowsException() {
        when(customerRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> customerService.getCustomerById(1L));

        verify(customerRepository, times(1)).findById(1L);
    }
}
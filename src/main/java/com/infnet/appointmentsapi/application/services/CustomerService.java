package com.infnet.appointmentsapi.application.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.infnet.appointmentsapi.domain.repositories.AddressRepository;
import com.infnet.appointmentsapi.domain.repositories.CustomerRepository;
import com.infnet.appointmentsapi.domain.repositories.UserRepository;
import com.infnet.appointmentsapi.infrastructure.models.Address;
import com.infnet.appointmentsapi.infrastructure.models.Customer;
import com.infnet.appointmentsapi.infrastructure.models.User;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(UserRepository userRepository, CustomerRepository customerRepository,
            AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer createCustomer(Customer customer, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        customer.setUser(user);

        Address address = customer.getAddress();
        if (address.getId() != null) {
            address = addressRepository.findById(customer.getAddress().getId()).orElse(null);
        }
        customer.setAddress(address);

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}

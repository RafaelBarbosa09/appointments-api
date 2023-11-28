package com.infnet.appointmentsapi.domain.repositories;

import com.infnet.appointmentsapi.infrastructure.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getByUserId(Long userId);
}

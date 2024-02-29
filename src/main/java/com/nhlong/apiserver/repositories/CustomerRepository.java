package com.nhlong.apiserver.repositories;

import com.nhlong.apiserver.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByNameEN(String name);
    Optional<Customer> findCustomerByIdAndStatus(long id, boolean status);
    List<Customer> findAllByStatus(boolean status);
}

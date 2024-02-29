package com.nhlong.apiserver.repositories;

import com.nhlong.apiserver.models.TranslationCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationCustomerRepository extends JpaRepository<TranslationCustomer, Long> {
}

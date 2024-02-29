package com.nhlong.apiserver.repositories;

import com.nhlong.apiserver.models.Customer;
import com.nhlong.apiserver.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String name);
    Optional<Product> findProductByIdAndStatus(long id, boolean status);
    List<Product> findAllByStatus(boolean status);
}

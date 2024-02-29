package com.nhlong.apiserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationProduct extends JpaRepository<com.nhlong.apiserver.models.TranslationProduct, Long> {
}

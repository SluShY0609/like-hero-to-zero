package com.example.likeherotozero.repository;

import com.example.likeherotozero.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByIsoCode(String isoCode);
}


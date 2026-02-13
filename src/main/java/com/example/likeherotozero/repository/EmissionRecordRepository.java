package com.example.likeherotozero.repository;

import com.example.likeherotozero.model.Country;
import com.example.likeherotozero.model.EmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmissionRecordRepository extends JpaRepository<EmissionRecord, Long> {

    // aktuellster Datensatz (höchstes Jahr) für ein Land
    Optional<EmissionRecord> findFirstByCountryOrderByYearDesc(Country country);
}

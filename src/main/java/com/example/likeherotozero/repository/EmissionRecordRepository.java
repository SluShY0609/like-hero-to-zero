package com.example.likeherotozero.repository;

import com.example.likeherotozero.model.Country;
import com.example.likeherotozero.model.EmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmissionRecordRepository extends JpaRepository<EmissionRecord, Long> {

    Optional<EmissionRecord> findFirstByCountryAndApprovedTrueOrderByYearDesc(Country country);

    Optional<EmissionRecord> findByCountryAndYear(Country country, Integer year);

    List<EmissionRecord> findAllByOrderByCountry_NameAscYearDesc();

    List<EmissionRecord> findAllByApprovedFalseOrderByCountry_NameAscYearDesc();
}

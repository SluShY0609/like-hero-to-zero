package com.example.likeherotozero.service;

import com.example.likeherotozero.model.Country;
import com.example.likeherotozero.model.EmissionRecord;
import com.example.likeherotozero.repository.CountryRepository;
import com.example.likeherotozero.repository.EmissionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmissionService {

    private final CountryRepository countryRepository;
    private final EmissionRecordRepository emissionRecordRepository;

    public EmissionService(CountryRepository countryRepository,
                           EmissionRecordRepository emissionRecordRepository) {
        this.countryRepository = countryRepository;
        this.emissionRecordRepository = emissionRecordRepository;
    }

    public Optional<EmissionRecord> getLatestEmissionByIsoCode(String isoCode) {

        Optional<Country> countryOpt = countryRepository.findByIsoCode(isoCode);

        if (countryOpt.isEmpty()) {
            return Optional.empty();
        }

        return emissionRecordRepository
                .findFirstByCountryOrderByYearDesc(countryOpt.get());
    }
}

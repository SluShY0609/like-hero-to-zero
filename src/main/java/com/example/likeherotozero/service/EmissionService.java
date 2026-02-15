package com.example.likeherotozero.service;

import com.example.likeherotozero.model.Country;
import com.example.likeherotozero.model.EmissionRecord;
import com.example.likeherotozero.repository.CountryRepository;
import com.example.likeherotozero.repository.EmissionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // Öffentlich: nur freigegebene Datensätze anzeigen
    public Optional<EmissionRecord> getLatestApprovedEmissionByIsoCode(String isoCode) {
        Optional<Country> countryOpt = countryRepository.findByIsoCode(isoCode);

        if (countryOpt.isEmpty()) {
            return Optional.empty();
        }

        return emissionRecordRepository.findFirstByCountryAndApprovedTrueOrderByYearDesc(countryOpt.get());
    }

    // Scientist: erstellt/ändert -> wird wieder "pending" (approved=false)
    public EmissionRecord saveOrUpdatePending(String isoCode, Integer year, Double co2Kilotons) {

        Country country = countryRepository.findByIsoCode(isoCode)
                .orElseThrow(() -> new IllegalArgumentException("Unbekannter ISO-Code: " + isoCode));

        EmissionRecord record = emissionRecordRepository.findByCountryAndYear(country, year)
                .orElseGet(EmissionRecord::new);

        record.setCountry(country);
        record.setYear(year);
        record.setCo2Kilotons(co2Kilotons);

        // jede Änderung muss neu freigegeben werden
        record.setApproved(false);

        return emissionRecordRepository.save(record);
    }

    // Scientist/Publisher: Listen
    public List<EmissionRecord> getAllRecords() {
        return emissionRecordRepository.findAllByOrderByCountry_NameAscYearDesc();
    }

    public Optional<EmissionRecord> getRecordById(Long id) {
        return emissionRecordRepository.findById(id);
    }

    // Publisher: pending list + approve
    public List<EmissionRecord> getPendingRecords() {
        return emissionRecordRepository.findAllByApprovedFalseOrderByCountry_NameAscYearDesc();
    }

    public void approveRecord(Long id) {
        EmissionRecord record = emissionRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Datensatz nicht gefunden: " + id));
        record.setApproved(true);
        emissionRecordRepository.save(record);
    }
}

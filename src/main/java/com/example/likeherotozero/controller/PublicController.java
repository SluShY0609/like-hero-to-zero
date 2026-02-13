package com.example.likeherotozero.controller;

import com.example.likeherotozero.model.EmissionRecord;
import com.example.likeherotozero.repository.CountryRepository;
import com.example.likeherotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PublicController {

    private final CountryRepository countryRepository;
    private final EmissionService emissionService;

    public PublicController(CountryRepository countryRepository, EmissionService emissionService) {
        this.countryRepository = countryRepository;
        this.emissionService = emissionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "index";
    }

    @GetMapping("/emission")
    public String emission(@RequestParam("iso") String isoCode, Model model) {
        Optional<EmissionRecord> recordOpt = emissionService.getLatestEmissionByIsoCode(isoCode);

        if (recordOpt.isEmpty()) {
            model.addAttribute("error", "Kein Datensatz gefunden für ISO-Code: " + isoCode);
            return "emission-detail";
        }

        model.addAttribute("record", recordOpt.get());
        return "emission-detail";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

package com.example.likeherotozero.controller;

import com.example.likeherotozero.repository.CountryRepository;
import com.example.likeherotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scientist")
public class ScientistController {

    private final CountryRepository countryRepository;
    private final EmissionService emissionService;

    public ScientistController(CountryRepository countryRepository, EmissionService emissionService) {
        this.countryRepository = countryRepository;
        this.emissionService = emissionService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "scientist-dashboard";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "scientist-add";
    }

    @PostMapping("/add")
    public String handleAdd(
            @RequestParam("iso") String isoCode,
            @RequestParam("year") Integer year,
            @RequestParam("co2") Double co2Kilotons,
            Model model
    ) {
        emissionService.saveOrUpdate(isoCode, year, co2Kilotons);
        model.addAttribute("message", "Datensatz gespeichert ✅");
        model.addAttribute("countries", countryRepository.findAll());
        return "scientist-add";
    }

    @GetMapping("/records")
    public String listRecords(Model model) {
        model.addAttribute("records", emissionService.getAllRecords());
        return "scientist-records";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        var recordOpt = emissionService.getRecordById(id);
        if (recordOpt.isEmpty()) {
            model.addAttribute("error", "Datensatz nicht gefunden.");
            return "scientist-records";
        }
        model.addAttribute("record", recordOpt.get());
        return "scientist-edit";
    }

    @PostMapping("/edit")
    public String handleEdit(
            @RequestParam("id") Long id,
            @RequestParam("co2") Double co2Kilotons
    ) {
        var recordOpt = emissionService.getRecordById(id);
        if (recordOpt.isEmpty()) {
            return "redirect:/scientist/records";
        }

        var record = recordOpt.get();
        emissionService.saveOrUpdate(
                record.getCountry().getIsoCode(),
                record.getYear(),
                co2Kilotons
        );

        return "redirect:/scientist/records";
    }
}

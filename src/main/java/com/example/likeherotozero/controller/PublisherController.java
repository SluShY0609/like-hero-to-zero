package com.example.likeherotozero.controller;

import com.example.likeherotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private final EmissionService emissionService;

    public PublisherController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping("/pending")
    public String pending(Model model) {
        model.addAttribute("records", emissionService.getPendingRecords());
        return "publisher-pending";
    }

    @PostMapping("/approve")
    public String approve(@RequestParam("id") Long id) {
        emissionService.approveRecord(id);
        return "redirect:/publisher/pending";
    }
}

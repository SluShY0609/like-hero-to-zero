package com.example.likeherotozero.model;

import jakarta.persistence.*;

@Entity
public class EmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Country country;

    @Column(name = "year_value", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Double co2Kilotons;

    @Column(nullable = false)
    private Boolean approved = false;

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getCo2Kilotons() {
        return co2Kilotons;
    }

    public void setCo2Kilotons(Double co2Kilotons) {
        this.co2Kilotons = co2Kilotons;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}

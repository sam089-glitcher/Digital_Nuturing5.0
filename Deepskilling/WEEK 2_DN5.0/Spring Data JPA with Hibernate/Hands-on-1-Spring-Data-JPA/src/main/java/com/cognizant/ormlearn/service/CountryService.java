package com.cognizant.ormlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Find country by code
    @Transactional(readOnly = true)
    public Country getCountry(String code) {
        return countryRepository.findById(code).orElse(null);
    }

    // Add new country
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Update country
    @Transactional
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    // Delete country
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Search countries by partial name
    @Transactional(readOnly = true)
    public List<Country> searchCountries(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    // Get all countries (optional but useful)
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
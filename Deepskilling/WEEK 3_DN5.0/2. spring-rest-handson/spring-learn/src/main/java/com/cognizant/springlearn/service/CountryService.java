package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.dao.CountryDao;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    // Get all countries
    public List<Country> getAllCountries() {
        return countryDao.getAllCountries();
    }

    // Get country by code
    public Country getCountry(String code) {

        List<Country> countryList = countryDao.getAllCountries();

        return countryList.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException());
    }
}
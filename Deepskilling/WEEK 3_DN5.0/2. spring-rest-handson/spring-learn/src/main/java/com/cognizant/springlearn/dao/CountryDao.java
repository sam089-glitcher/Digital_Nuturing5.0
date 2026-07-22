package com.cognizant.springlearn.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Country;

@Repository
public class CountryDao {

    @Autowired
    @Qualifier("countryList")
    private List<Country> countryList;

    public List<Country> getAllCountries() {
        return countryList;
    }

}
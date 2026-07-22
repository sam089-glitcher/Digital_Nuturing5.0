package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Search by containing text
    List<Country> findByNameContaining(String text);

    // Search by containing text + ascending order
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Search by starting alphabet
    List<Country> findByNameStartingWith(String alphabet);

}
package com.cognizant.ormlearn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    Optional<Country> findByCode(String code);

    List<Country> findByNameContainingIgnoreCase(String name);

}
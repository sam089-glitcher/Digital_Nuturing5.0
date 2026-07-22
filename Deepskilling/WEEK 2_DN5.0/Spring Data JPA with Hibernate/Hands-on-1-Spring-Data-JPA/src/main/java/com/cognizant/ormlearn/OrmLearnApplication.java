package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;

	public static void main(String[] args) {

		ApplicationContext context =
				SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);

		LOGGER.info("Inside main");

		// Uncomment ONLY ONE method at a time

		testSearchCountry();

		// testSearchCountrySorted();

		// testSearchCountryStartingWith();
	}

	/**
	 * Search countries containing "ou"
	 */
	private static void testSearchCountry() {

		LOGGER.info("Start");

		List<Country> countries = countryService.searchCountries("ou");

		countries.forEach(country -> LOGGER.info("{}", country));

		LOGGER.info("End");
	}

	/**
	 * Search countries containing "ou" and sort in ascending order
	 */
	private static void testSearchCountrySorted() {

		LOGGER.info("Start");

		List<Country> countries = countryService.searchCountriesSorted("ou");

		countries.forEach(country -> LOGGER.info("{}", country));

		LOGGER.info("End");
	}

	/**
	 * Search countries starting with "Z"
	 */
	private static void testSearchCountryStartingWith() {

		LOGGER.info("Start");

		List<Country> countries = countryService.searchCountriesStartingWith("Z");

		countries.forEach(country -> LOGGER.info("{}", country));

		LOGGER.info("End");
	}
}
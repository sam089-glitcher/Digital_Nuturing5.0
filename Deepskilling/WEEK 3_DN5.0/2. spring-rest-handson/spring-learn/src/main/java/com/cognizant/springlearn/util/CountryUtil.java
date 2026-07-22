package com.cognizant.springlearn.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.springlearn.model.Country;

public class CountryUtil {

    @SuppressWarnings("unchecked")
    public static List<Country> getCountryList() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        return (List<Country>) context.getBean("countryList");
    }

}
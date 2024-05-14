package com.example.univerproject.test_controller;
import com.example.univerproject.controller.CountryController;
import com.example.univerproject.model.Country;
import com.example.univerproject.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TestCountryController {

    @Mock
    private CountryService countryService;

    private CountryController countryController;

    @BeforeEach


    @Test
    void testGetAllCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryService.getAllCountries()).thenReturn(countries);

        String expectedViewName = "countries";
        Model model = new ExtendedModelMap();

        String viewName = countryController.getAllCountries(model);

        assertEquals(expectedViewName, viewName);
        assertNotNull(model.getAttribute("countries"));
        List<Country> responseCountries = (List<Country>) model.getAttribute("countries");
        assertEquals(2, responseCountries.size());
    }

//    @Test
//    void testGetCountryById() {
//        Long id = 1L;
//        Country country = new Country();
//        country.setId(id);
//
//        when(countryService.getCountryById(id)).thenReturn(country);
//
//        ResponseEntity<Country> response = countryController.getCountryById(id);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(id, response.getBody().getId());
//    }

//    @Test
//    void testCreateCountry() {
//        Country country = new Country();
//        country.setId(1L);
//
//        when(countryService.createCountry(country)).thenReturn(country);
//
//        ResponseEntity<Country> response = countryController.createCountry(country);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(1L, response.getBody().getId());
//    }



    @Test
    void testPerformBulkCountryOperation() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryService.performBulkCountryOperation(countries)).thenReturn(countries);

        ResponseEntity<List<Country>> response = countryController.performBulkCountryOperation(countries);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
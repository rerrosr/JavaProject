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
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryController = new CountryController(countryService);
    }

    @Test
    void testGetAllCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryService.getAllCountries()).thenReturn(countries);

        ResponseEntity<List<Country>> response = countryController.getAllCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetCountryById() {
        Long id = 1L;
        Country country = new Country();
        country.setId(id);

        when(countryService.getCountryById(id)).thenReturn(country);

        ResponseEntity<Country> response = countryController.getCountryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void testCreateCountry() {
        Country country = new Country();
        country.setId(1L);

        when(countryService.createCountry(country)).thenReturn(country);

        ResponseEntity<Country> response = countryController.createCountry(country);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testUpdateCountry() {
        Long id = 1L;
        Country updatedCountry = new Country();
        updatedCountry.setId(id);
        Country savedCountry = new Country();
        savedCountry.setId(id);

        when(countryService.updateCountry(id, updatedCountry)).thenReturn(savedCountry);

        ResponseEntity<Country> response = countryController.updateCountry(id, updatedCountry);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void testDeleteCountry() {
        Long id = 1L;

        ResponseEntity<Void> response = countryController.deleteCountry(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(countryService, times(1)).deleteCountry(id);
    }

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
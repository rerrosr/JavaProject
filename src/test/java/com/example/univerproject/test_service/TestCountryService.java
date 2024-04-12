package com.example.univerproject.test_service;
import com.example.univerproject.cache.MyCache;
import com.example.univerproject.model.Country;
import com.example.univerproject.repositories.CountryRepository;
import com.example.univerproject.service.CountryService;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TestCountryService {
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private MyCache<Long, Country> cache;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryService = new CountryService(countryRepository);
    }

    @Test
    void testGetAllCountries() {
        List<Country> countries = new ArrayList<>();
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = countryService.getAllCountries();

        assertEquals(countries, result);
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetCountryByIdFromRepository() {
        Long id = 1L;
        Country country = new Country();
        country.setId(id);

        when(countryRepository.findById(id)).thenReturn(Optional.of(country));

        Country result = countryService.getCountryById(id);

        assertEquals(country, result);
        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    void testGetCountryByIdNotFound() {
        Long id = 1L;

        when(countryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> countryService.getCountryById(id));

        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    void testCreateCountry() {
        Country country = new Country();

        when(countryRepository.save(country)).thenReturn(country);

        Country result = countryService.createCountry(country);

        assertEquals(country, result);
        verify(countryRepository, times(1)).save(country);
    }

    @Test
    void testUpdateCountry() {
        Long id = 1L;
        Country existingCountry = new Country();
        existingCountry.setId(id);

        @NotNull
        Country updatedCountry = new Country();
        updatedCountry.setName("Updated Country");
        updatedCountry.setPopulation(1000000);
        updatedCountry.setArea(1000.5f);

        when(countryRepository.save(existingCountry)).thenReturn(existingCountry);
        when(countryRepository.findById(id)).thenReturn(Optional.of(existingCountry));

        Country result = countryService.updateCountry(id, updatedCountry);

        assertEquals(existingCountry, result);
        assertEquals(updatedCountry.getName(), existingCountry.getName());
        assertEquals(updatedCountry.getPopulation(), existingCountry.getPopulation());
        assertEquals(updatedCountry.getArea(), existingCountry.getArea());
        verify(countryRepository, times(1)).findById(id);
        verify(countryRepository, times(1)).save(existingCountry);
    }

    @Test
    void testDeleteCountry() {
        Long id = 1L;

        countryService.deleteCountry(id);

        verify(countryRepository, times(1)).deleteById(id);
    }

    @Test
    void testPerformBulkCountryOperation() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryRepository.save(any(Country.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Country> result = countryService.performBulkCountryOperation(countries);

        assertEquals(countries, result);
        verify(countryRepository, times(2)).save(any(Country.class));
    }
}

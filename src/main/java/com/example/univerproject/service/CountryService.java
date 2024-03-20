package com.example.univerproject.service;

import com.example.univerproject.model.Country;
import com.example.univerproject.repositories.CountryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, @NotNull Country updatedCountry) {
        Country country = getCountryById(id);
        country.setName(updatedCountry.getName());
        country.setPopulation(updatedCountry.getPopulation());
        country.setArea(updatedCountry.getArea());
        return countryRepository.save(country);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
package com.example.univerproject.controller;

import com.example.univerproject.model.Country;
import com.example.univerproject.service.CountryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Country controller. */
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
  private final CountryService countryService;

  /**
   * Instantiates a new Country controller.
   *
   * @param countryService the country service
   */
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  /**
   * Gets all countries.
   *
   * @return the all countries
   */
  @GetMapping
  public ResponseEntity<List<Country>> getAllCountries() {
    List<Country> countries = countryService.getAllCountries();
    return ResponseEntity.ok(countries);
  }

  /**
   * Gets country by id.
   *
   * @param id the id
   * @return the country by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
    Country country = countryService.getCountryById(id);
    return ResponseEntity.ok(country);
  }

  /**
   * Create country response entity.
   *
   * @param country the country
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Country> createCountry(@RequestBody Country country) {
    Country createdCountry = countryService.createCountry(country);
    return ResponseEntity.ok(createdCountry);
  }

  /**
   * Update country response entity.
   *
   * @param id the id
   * @param updatedCountry the updated country
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Country> updateCountry(
      @PathVariable Long id, @RequestBody Country updatedCountry) {
    Country country = countryService.updateCountry(id, updatedCountry);
    return ResponseEntity.ok(country);
  }

  /**
   * Delete country response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
    countryService.deleteCountry(id);
    return ResponseEntity.noContent().build();
  }
}

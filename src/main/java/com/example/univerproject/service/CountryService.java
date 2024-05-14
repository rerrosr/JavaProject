package com.example.univerproject.service;

import com.example.univerproject.cache.MyCache;
import com.example.univerproject.model.Country;
import com.example.univerproject.repositories.CountryRepository;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/** The type Country service. */
@Service
@Hidden
public class CountryService {
  private final CountryRepository countryRepository;
  private final MyCache<Long, Country> cache = new MyCache<>(20);

  /**
   * Instantiates a new Country service.
   *
   * @param countryRepository the country repository
   */
  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  /**
   * Gets all countries.
   *
   * @return the all countries
   */
  public List<Country> getAllCountries() {
    return countryRepository.findAll();
  }

  /**
   * Gets country by id.
   *
   * @param id the id
   * @return the country by id
   */
  public Country getCountryById(Long id) {
    return countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Country not found"));
  }
//  public Country getCountryById(Long id) {
//    Country country = cache.get(id);
//    if (country == null) {
//      country =
//              countryRepository
//                      .findById(id)
//                      .orElseThrow(() -> new RuntimeException("Country not found"));
//      if (country != null) {
//        cache.put(id, country);
//      }
//    }
//    return country;
//  }

  /**
   * Create country country.
   *
   * @param country the country
   * @return the country
   */
  public Country createCountry(Country country) {
    return countryRepository.save(country);
  }

  /**
   * Update country country.
   *
   * @param id the id
   * @param updatedCountry the updated country
   * @return the country
   */
  public Country updateCountry(Long id, @NotNull Country updatedCountry) {
    Country country = getCountryById(id);
    country.setName(updatedCountry.getName());
    country.setPopulation(updatedCountry.getPopulation());
    country.setArea(updatedCountry.getArea());
    return countryRepository.save(country);
  }

  /**
   * Delete country.
   *
   * @param id the id
   */
  public void deleteCountry(Long id) {
    countryRepository.deleteById(id);
    cache.remove(id);
  }

  /**
   * Create bulk country.
   *
   * @return list of country
   */
  public List<Country> performBulkCountryOperation(List<Country> countries) {
    return countries.stream()
            .map(country -> (countryRepository.save(country)))
            .toList();
  }
}
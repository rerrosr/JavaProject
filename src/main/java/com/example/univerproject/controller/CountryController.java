package com.example.univerproject.controller;

import com.example.univerproject.model.University;
import com.example.univerproject.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.univerproject.model.Country;
import com.example.univerproject.service.CountryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** The type Country controller. */
@Controller
//@RequestMapping("/api/v1/countries")
public class CountryController {
  private final CountryService countryService;
  private final UniversityService universityService;

  public CountryController(CountryService countryService, UniversityService universityService) {
    this.countryService = countryService;
    this.universityService = universityService;
  }

  /**
   * Gets all countries.
   *
   * @return the all countries
   */
  @GetMapping("/all")
  public String getAllCountries(Model model) {
    List<Country> countries = countryService.getAllCountries();
    List<University> universities = universityService.getAllUniversities();
    model.addAttribute("countries", countries);
    model.addAttribute("universities", universities);
    return "allInfo";
  }

  /**
   * Gets country by id.
   *
   * @param id the id
   * @return the country by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Country> getCountryById(@PathVariable String id) {
    try {
      Long countryId = Long.parseLong(id);
      Country country = countryService.getCountryById(countryId);
      if (country == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(country);
    } catch (NumberFormatException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Create country response entity.
   *
   * @param country the country
   * @return the response entity
   */

  @PostMapping()
  public ResponseEntity<Country> createCountry(@RequestBody Country country) {
    Country createdCountry = countryService.createCountry(country);
    return ResponseEntity.ok(createdCountry);
  }


  /**
   * Create country response entity.
   *
   * @return the response entity
   */
  @PostMapping("/bulk")
  public ResponseEntity<List<Country>> performBulkCountryOperation(@RequestBody List<Country> countries) {
    List<Country> createdCountries = countryService.performBulkCountryOperation(countries);
    return ResponseEntity.ok(createdCountries);
  }

  @GetMapping("/addCountry")
  public String showAddCountryForm(Model model) {
    model.addAttribute("country", new Country());
    return "addCountry";
  }

  @PostMapping("/addCountry")
  public String addCountry(@ModelAttribute("country") Country country) {
    countryService.createCountry(country);
    return "redirect:/all";
  }
  @GetMapping("/delete")
  public String deleteAllCountries(Model model) {
    List<Country> countries = countryService.getAllCountries();
    model.addAttribute("countries", countries);
    return "deleteCountry";
  }
  @PostMapping("/delete/{id}")
  public String deleteCountry(@PathVariable Long id) {
    countryService.deleteCountry(id);
    return "redirect:/all";
  }
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable("id") Long id, Model model) {
    Country country = countryService.getCountryById(id);
    if (country == null) {
      return "error";
    }
    model.addAttribute("country", country);
    return "updateCountry";
  }

  @PostMapping("/edit/{id}")
  public String editCountry(@PathVariable("id") Long id, @ModelAttribute("updatedCountry") @Validated Country updatedCountry, BindingResult result) {
    if (result.hasErrors()) {
      return "updateCountry";
    }
    Country country = countryService.updateCountry(id, updatedCountry);
    if (country == null) {
      return "error";
    }
    return "redirect:/all";
  }
}

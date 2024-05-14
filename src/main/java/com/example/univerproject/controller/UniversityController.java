package com.example.univerproject.controller;

import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import com.example.univerproject.service.CountryService;
import com.example.univerproject.service.UniversityService;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** The type University controller. */
@Controller
@RequestMapping("/api")
public class UniversityController {
  private final UniversityService universityService;
  private final CountryService countryService;

  /**
   * Instantiates a new University controller.
   *
   * @param universityService the university service
   */
  public UniversityController(UniversityService universityService, CountryService countryService) {
    this.universityService = universityService;
    this.countryService = countryService;
  }

  /**
   * Gets university.
   *
   * @param country the country
   * @return the university
   */
  @GetMapping("/find")
  public ResponseEntity<JsonNode> getUniversity(@RequestParam("country") String country) {
    JsonNode response = universityService.getUniversity(country);
    return ResponseEntity.ok(response);
  }

  /**
   * Gets all universities.
   *
   * @return the all universities
   */
  @GetMapping
  public ResponseEntity<List<University>> getAllUniversities() {
    List<University> universities = universityService.getAllUniversities();
    return ResponseEntity.ok(universities);
  }

  /**
   * Gets university by id.
   *
   * @param id the id
   * @return the university by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
    University university = universityService.getUniversityById(id);
    return ResponseEntity.ok(university);
  }

  @GetMapping("/addUniversity")
  public String showAddUniversityForm(Model model) {
    List<Country> countries = countryService.getAllCountries();
    model.addAttribute("countries", countries);
    model.addAttribute("university", new University());
    return "addUniversity";
  }

  @PostMapping("/addUniversity")
  public String addUniversity(@ModelAttribute University university) {
    String universityName = university.getName();
    Long countryId = university.getCountry().getId();
    universityService.createUniversity(universityName, countryId);
    return "redirect:/all";
  }

  /**
   * Gets universities by country name.
   *
   * @param countryName the country name
   * @return the universities by country name
   */
  @GetMapping("/search")
  public ResponseEntity<List<University>> getUniversitiesByCountryName(
      @RequestParam("country") String countryName) {
    List<University> universities = universityService.getUniversitiesByCountryName(countryName);
    return ResponseEntity.ok(universities);
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<University>> performBulkOperations(@RequestBody List<University> universities) {
    List<University> result = universityService.performBulkOperations(universities);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/deleteUniversity")
  public String showDeleteUniversityForm(Model model) {
    List<University> university = universityService.getAllUniversities();
    model.addAttribute("university", university);
    return "deleteUniversity";
  }
  @PostMapping("/deleteUniversity/{id}")
  public String deleteCountry(@PathVariable("id") Long id) {
    universityService.deleteUniversity(id);
    return "redirect:/all";
  }

  @GetMapping("/editUniversity/{id}")
  public String showEditUniversityForm(@PathVariable("id") Long id, Model model) {
    University university = universityService.getUniversityById(id);
    List<Country> country = countryService.getAllCountries();
    model.addAttribute("university", university);
    model.addAttribute("countries", country);
    return "editUniversity";
  }

  @PostMapping("/editUniversity/{id}")
  public String editUniversity(@PathVariable("id") Long id, @ModelAttribute("university") University updatedUniversity) {
    universityService.updateUniversity(id, updatedUniversity);
    return "redirect:/all";
  }
}

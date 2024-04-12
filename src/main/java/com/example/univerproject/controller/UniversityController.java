package com.example.univerproject.controller;

import com.example.univerproject.model.University;
import com.example.univerproject.service.UniversityService;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The type University controller. */
@RestController
@RequestMapping("/api/v1/university")
public class UniversityController {
  private final UniversityService universityService;

  /**
   * Instantiates a new University controller.
   *
   * @param universityService the university service
   */
  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
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

  /**
   * Create university response entity.
   *
   * @param requestBody the request body
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<University> createUniversity(@RequestBody Map<String, Object> requestBody) {
    String universityName = (String) requestBody.get("name");
    Long countryId = Long.parseLong(requestBody.get("countryId").toString());

    University createdUniversity = universityService.createUniversity(universityName, countryId);
    return ResponseEntity.ok(createdUniversity);
  }

  /**
   * Update university response entity.
   *
   * @param id the id
   * @param updatedUniversity the updated university
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<University> updateUniversity(
      @PathVariable Long id, @RequestBody University updatedUniversity) {
    University university = universityService.updateUniversity(id, updatedUniversity);
    return ResponseEntity.ok(university);
  }

  /**
   * Delete university response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
    universityService.deleteUniversity(id);
    return ResponseEntity.noContent().build();
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
<<<<<<< Updated upstream

  @PostMapping("/bulk")
  public ResponseEntity<List<University>> performBulkOperations(@RequestBody List<University> universities) {
    List<University> result = universityService.performBulkOperations(universities);

    return ResponseEntity.ok(result);
  }
=======
>>>>>>> Stashed changes
}

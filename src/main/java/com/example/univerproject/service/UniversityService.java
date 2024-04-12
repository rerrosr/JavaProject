package com.example.univerproject.service;

import com.example.univerproject.cache.MyCache;
import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import com.example.univerproject.repositories.UniversityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/** The type University service. */
@Service
@AllArgsConstructor
@Hidden
public class UniversityService {
  private final UniversityRepository universityRepository;
  private final RestTemplate restTemplate;
  private final CountryService countryService;

  private static String universitiesApiUrl = "http://universities.hipolabs.com/search";
  private final MyCache<Long, University> cache = new MyCache<>(20);

  /**
   * Gets university.
   *
   * @param country the country
   * @return the university
   */
  public JsonNode getUniversity(String country) {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(universitiesApiUrl).queryParam("country", country);
    String url = builder.toUriString();
    ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
    return response.getBody();
  }

  /**
   * Create university university.
   *
   * @param universityName the university name
   * @param countryId the country id
   * @return the university
   */
  public University createUniversity(String universityName, Long countryId) {
    Country country = countryService.getCountryById(countryId);
    University university = new University(universityName, country);
    return universityRepository.save(university);
  }

  /**
   * Gets all universities.
   *
   * @return the all universities
   */
  public List<University> getAllUniversities() {
    return universityRepository.findAll();
  }

  /**
   * Gets university by id.
   *
   * @param id the id
   * @return the university by id
   */
  public University getUniversityById(Long id) {
    University university = cache.get(id);
    if (university == null) {
      university =
          universityRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("University not found"));
      if (university != null) {
        cache.put(id, university);
      }
    }
    return university;
  }

  /**
   * Update university university.
   *
   * @param id the id
   * @param updatedUniversity the updated university
   * @return the university
   */
  public University updateUniversity(Long id, University updatedUniversity) {
    University university = getUniversityById(id);
    university.setName(updatedUniversity.getName());
    return universityRepository.save(university);
  }

  /**
   * Delete university.
   *
   * @param id the id
   */
  public void deleteUniversity(Long id) {
    universityRepository.deleteById(id);
    cache.remove(id);
  }

  /**
   * Gets universities by country name.
   *
   * @param countryName the country name
   * @return the universities by country name
   */
  public List<University> getUniversitiesByCountryName(String countryName) {
    return universityRepository.findByCountryName(countryName);
  }
<<<<<<< Updated upstream

  public List<University> performBulkOperations(List<University> universities) {
    return universities.stream()
            .map(university -> createUniversity(university.getName(), university.getCountry().getId()))
            .toList();
  }
=======
>>>>>>> Stashed changes
}

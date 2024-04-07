package com.example.univerproject.service;

import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import com.example.univerproject.repositories.CountryRepository;
import com.example.univerproject.repositories.UniversityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final RestTemplate restTemplate;
    private final CountryService countryService;

    private static String universitiesApiUrl = "http://universities.hipolabs.com/search";

    public JsonNode getUniversity(String country) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(universitiesApiUrl)
                .queryParam("country", country);
        String url = builder.toUriString();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        return response.getBody();
    }
    public University createUniversity(String universityName, Long countryId) {
        Country country = countryService.getCountryById(countryId);
        University university = new University(universityName, country);
        return universityRepository.save(university);
    }
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
    }

    public University updateUniversity(Long id, University updatedUniversity) {
        University university = getUniversityById(id);
        university.setName(updatedUniversity.getName());
        return universityRepository.save(university);
    }
    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }

<<<<<<< HEAD
}
=======
    public List<University> getUsefulUniversities(String country) {
        return universityRepository.findUsefulUniversities(country);
    }
}
>>>>>>> 2d6d35182bdd31b0afc2e475e0fed728221ee68d

package com.example.univerproject.test_service;
import com.example.univerproject.cache.MyCache;
import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import com.example.univerproject.repositories.UniversityRepository;
import com.example.univerproject.service.CountryService;
import com.example.univerproject.service.UniversityService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestUniversityService {

    private UniversityService universityService;

    @Mock
    private UniversityRepository universityRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CountryService countryService;

    @Mock
    private MyCache<Long, University> cache;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        universityService = new UniversityService(universityRepository, restTemplate, countryService);
//    }

    @Test
    void testGetUniversity() {
        String country = "USA";
        ResponseEntity<JsonNode> responseEntity = mock(ResponseEntity.class);
        JsonNode responseBody = mock(JsonNode.class);
        when(restTemplate.getForEntity(anyString(), eq(JsonNode.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(responseBody);

        JsonNode result = universityService.getUniversity(country);

        assertEquals(responseBody, result);
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(JsonNode.class));
    }

    @Test
    void testCreateUniversity() {
        String universityName = "Test University";
        Long countryId = 1L;
        Country country = new Country();
        country.setId(countryId);
        University createdUniversity = new University(universityName, country);
        when(countryService.getCountryById(countryId)).thenReturn(country);
        when(universityRepository.save(any(University.class))).thenReturn(createdUniversity);

        University result = universityService.createUniversity(universityName, countryId);

        assertEquals(createdUniversity, result);
        verify(countryService, times(1)).getCountryById(countryId);
        verify(universityRepository, times(1)).save(any(University.class));
    }

    @Test
    void testGetAllUniversities() {
        List<University> universities = new ArrayList<>();
        universities.add(new University("University 1", new Country()));
        universities.add(new University("University 2", new Country()));
        when(universityRepository.findAll()).thenReturn(universities);

        List<University> result = universityService.getAllUniversities();

        assertEquals(universities, result);
        verify(universityRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUniversity() {
        Long id = 1L;
        String updatedName = "Updated University";
        University existingUniversity = new University("Old University", new Country());
        existingUniversity.setId(id);
        University updatedUniversity = new University(updatedName, existingUniversity.getCountry());
        when(universityRepository.findById(id)).thenReturn(Optional.of(existingUniversity));
        when(universityRepository.save(any(University.class))).thenReturn(updatedUniversity);

        University result = universityService.updateUniversity(id, updatedUniversity);

        assertEquals(updatedUniversity, result);
        assertEquals(updatedName, result.getName());
        verify(universityRepository, times(1)).findById(id);
        verify(universityRepository, times(1)).save(any(University.class));
    }

    @Test
    void testGetUniversitiesByCountryName() {
        String countryName = "USA";
        List<University> universities = new ArrayList<>();
        universities.add(new University("University 1", new Country()));
        universities.add(new University("University 2", new Country()));
        when(universityRepository.findByCountryName(countryName)).thenReturn(universities);

        List<University> result = universityService.getUniversitiesByCountryName(countryName);

        assertEquals(universities, result);
        verify(universityRepository, times(1)).findByCountryName(countryName);
    }

    @Test
    void testPerformBulkOperations() {
        List<University> universities = new ArrayList<>();
        Country country = new Country();
        country.setId(1L);
        universities.add(new University("University 1", country));
        universities.add(new University("University 2", country));
        when(countryService.getCountryById(1L)).thenReturn(country);
        when(universityRepository.save(any(University.class))).thenReturn(new University());

        List<University> result = universityService.performBulkOperations(universities);

        assertEquals(universities.size(), result.size());
        verify(countryService, times(universities.size())).getCountryById(1L);
        verify(universityRepository, times(universities.size())).save(any(University.class));
    }
}

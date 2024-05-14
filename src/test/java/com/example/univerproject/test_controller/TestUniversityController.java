package com.example.univerproject.test_controller;
import com.example.univerproject.controller.UniversityController;
import com.example.univerproject.model.University;
import com.example.univerproject.service.UniversityService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestUniversityController {

    @Mock
    private UniversityService universityService;

    private UniversityController universityController;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        universityController = new UniversityController(universityService);
//    }

    @Test
    void testGetUniversity() {
        String country = "Country";
        JsonNode response = mock(JsonNode.class);
        when(universityService.getUniversity(country)).thenReturn(response);

        ResponseEntity<JsonNode> expected = ResponseEntity.ok(response);
        ResponseEntity<JsonNode> actual = universityController.getUniversity(country);

        assertEquals(expected, actual);
        verify(universityService).getUniversity(country);
    }

//    @Test
//    void testGetAllUniversities() {
//        List<University> universities = Collections.singletonList(mock(University.class));
//        when(universityService.getAllUniversities()).thenReturn(universities);
//
//        Model model = new ExtendedModelMap();
//        String expectedViewName = "universityList";
//        String actualViewName = universityController.getAllUniversities(model);
//
//        assertEquals(expectedViewName, actualViewName);
//        assertEquals(universities, model.getAttribute("universities"));
//        verify(universityService).getAllUniversities();
//    }

    @Test
    void testGetUniversityById() {
        Long id = 1L;
        University university = mock(University.class);
        when(universityService.getUniversityById(id)).thenReturn(university);

        ResponseEntity<University> expected = ResponseEntity.ok(university);
        ResponseEntity<University> actual = universityController.getUniversityById(id);

        assertEquals(expected, actual);
        verify(universityService).getUniversityById(id);
    }

//    @Test
//    void testCreateUniversity() {
//        String universityName = "University";
//        Long countryId = 1L;
//        University createdUniversity = mock(University.class);
//        Map<String, Object> requestBody = Map.of("name", universityName, "countryId", countryId);
//        when(universityService.createUniversity(universityName, countryId)).thenReturn(createdUniversity);
//
//        ResponseEntity<University> expected = ResponseEntity.ok(createdUniversity);
//        ResponseEntity<University> actual = universityController.createUniversity(requestBody);
//
//        assertEquals(expected, actual);
//        verify(universityService).createUniversity(universityName, countryId);
//    }

//    @Test
//    void testUpdateUniversity() {
//        Long id = 1L;
//        University updatedUniversity = mock(University.class);
//        University university = mock(University.class);
//        when(universityService.updateUniversity(id, updatedUniversity)).thenReturn(university);
//
//        ResponseEntity<University> expected = ResponseEntity.ok(university);
//        ResponseEntity<University> actual = universityController.updateUniversity(id, updatedUniversity);
//
//        assertEquals(expected, actual);
//        verify(universityService).updateUniversity(id, updatedUniversity);
//    }

//    @Test
//    void testDeleteUniversity() {
//        Long id = 1L;
//        ResponseEntity<Void> expected = ResponseEntity.noContent().build();
//        ResponseEntity<Void> actual = universityController.deleteUniversity(id);
//
//        assertEquals(expected, actual);
//        verify(universityService).deleteUniversity(id);
//    }

    @Test
    void testGetUniversitiesByCountryName() {
        String countryName = "Country";
        List<University> universities = Collections.singletonList(mock(University.class));
        when(universityService.getUniversitiesByCountryName(countryName)).thenReturn(universities);

        ResponseEntity<List<University>> expected = ResponseEntity.ok(universities);
        ResponseEntity<List<University>> actual = universityController.getUniversitiesByCountryName(countryName);

        assertEquals(expected, actual);
        verify(universityService).getUniversitiesByCountryName(countryName);
    }

    @Test
    void testPerformBulkOperations() {
        List<University> universities = Collections.singletonList(mock(University.class));
        List<University> result = Collections.singletonList(mock(University.class));
        when(universityService.performBulkOperations(universities)).thenReturn(result);

        ResponseEntity<List<University>> expected = ResponseEntity.ok(result);
        ResponseEntity<List<University>> actual = universityController.performBulkOperations(universities);

        assertEquals(expected, actual);
        verify(universityService).performBulkOperations(universities);
    }
}
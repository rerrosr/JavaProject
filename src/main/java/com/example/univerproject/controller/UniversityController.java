package com.example.univerproject.controller;

import com.example.univerproject.model.University;
import com.example.univerproject.service.UniversityService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/search")
    public ResponseEntity<JsonNode> getUniversity(@RequestParam("country") String country) {
        JsonNode response = universityService.getUniversity(country);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        University university = universityService.getUniversityById(id);
        return ResponseEntity.ok(university);
    }

    @PostMapping
    public ResponseEntity<University> createUniversity(@RequestBody Map<String, Object> requestBody) {
        String universityName = (String) requestBody.get("name");
        Long countryId = Long.parseLong(requestBody.get("countryId").toString());

        University createdUniversity = universityService.createUniversity(universityName, countryId);
        return ResponseEntity.ok(createdUniversity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University updatedUniversity) {
        University university = universityService.updateUniversity(id, updatedUniversity);
        return ResponseEntity.ok(university);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }

}
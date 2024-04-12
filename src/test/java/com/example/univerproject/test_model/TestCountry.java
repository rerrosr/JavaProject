package com.example.univerproject.test_model;

import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TestCountry {

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
    }

    @Test
    void testId() {
        Long id = 1L;
        country.setId(id);
        assertEquals(id, country.getId());
    }

    @Test
    void testName() {
        String name = "Country";
        country.setName(name);
        assertEquals(name, country.getName());
    }

    @Test
    void testPopulation() {
        int population = 1000000;
        country.setPopulation(population);
        assertEquals(population, country.getPopulation());
    }

    @Test
    void testArea() {
        float area = 1000.5f;
        country.setArea(area);
        assertEquals(area, country.getArea(), 0.01);
    }

    @Test
    void testUniversityList() {
        List<University> universities = new ArrayList<>();
        universities.add(new University());
        universities.add(new University());
        country.setUniversityList(universities);
        assertEquals(universities, country.getUniversityList());
    }

    @Test
    void testEquals() {
        Country country1 = new Country();
        country1.setName("Country");

        Country country2 = new Country();
        country2.setName("Country");

        Country country3 = new Country();
        country3.setName("Another Country");

        assertEquals(country1, country2);
        assertNotEquals(country1, country3);
        assertNotEquals(country2, country3);
    }

    @Test
    void testHashCode() {
        Country country1 = new Country();
        country1.setName("Country");

        Country country2 = new Country();
        country2.setName("Country");

        assertEquals(country1.hashCode(), country2.hashCode());
    }
}


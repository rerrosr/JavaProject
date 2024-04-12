package com.example.univerproject.test_model;


import com.example.univerproject.model.Country;
import com.example.univerproject.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TestUniversity {
    private University university;

    @BeforeEach
    void setUp() {
        university = new University();
    }

    @Test
    void testId() {
        Long id = 1L;
        university.setId(id);
        assertEquals(id, university.getId());
    }

    @Test
    void testName() {
        String name = "University";
        university.setName(name);
        assertEquals(name, university.getName());
    }

    @Test
    void testCountry() {
        Country country = new Country();
        university.setCountry(country);
        assertEquals(country, university.getCountry());
    }

    @Test
    void testEquals() {
        University university1 = new University();
        university1.setName("University");

        University university2 = new University();
        university2.setName("University");

        University university3 = new University();
        university3.setName("Another University");

        assertEquals(university1, university2);
        assertNotEquals(university1, university3);
        assertNotEquals(university2, university3);
    }

    @Test
    void testHashCode() {
        University university1 = new University();
        university1.setName("University");

        University university2 = new University();
        university2.setName("University");

        assertEquals(university1.hashCode(), university2.hashCode());
    }
}

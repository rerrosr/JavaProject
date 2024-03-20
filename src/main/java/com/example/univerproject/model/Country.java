package com.example.univerproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "population", nullable = false)
    private int population;

    @Column(name = "area", nullable = false)
    private float area;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<University> universityList = new ArrayList<>();
    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public List<University> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<University> universityList) {
        this.universityList = universityList;
    }

}
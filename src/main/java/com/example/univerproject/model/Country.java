package com.example.univerproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
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
}

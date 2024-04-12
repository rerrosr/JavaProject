package com.example.univerproject.model;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The type Country. */
@Entity
@Hidden
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(name, country.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /** Instantiates a new Country. */
<<<<<<< Updated upstream
  public Country() {
    // No initialization nedeed for this consrtuctor
  }
=======
  public Country() {}
>>>>>>> Stashed changes

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets population.
   *
   * @return the population
   */
  public int getPopulation() {
    return population;
  }

  /**
   * Sets population.
   *
   * @param population the population
   */
  public void setPopulation(int population) {
    this.population = population;
  }

  /**
   * Gets area.
   *
   * @return the area
   */
  public float getArea() {
    return area;
  }

  /**
   * Sets area.
   *
   * @param area the area
   */
  public void setArea(float area) {
    this.area = area;
  }

  /**
   * Gets university list.
   *
   * @return the university list
   */
  public List<University> getUniversityList() {
    return universityList;
  }

  /**
   * Sets university list.
   *
   * @param universityList the university list
   */
  public void setUniversityList(List<University> universityList) {
    this.universityList = universityList;
  }
}

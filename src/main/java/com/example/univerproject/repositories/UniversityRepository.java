package com.example.univerproject.repositories;

import com.example.univerproject.model.University;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface University repository. */
@Repository
@Hidden
public interface UniversityRepository extends JpaRepository<University, Long> {
  /**
   * Find by country name list.
   *
   * @param countryName the country name
   * @return the list
   */
  @Query("SELECT u FROM University u WHERE u.country.name = :countryName")
  List<University> findByCountryName(@Param("countryName") String countryName);
}

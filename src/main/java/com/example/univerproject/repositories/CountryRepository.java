package com.example.univerproject.repositories;

import com.example.univerproject.model.Country;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** The interface Country repository. */
@Repository
@Hidden
public interface CountryRepository extends JpaRepository<Country, Long> {}

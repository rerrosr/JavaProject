package com.example.univerproject.repositories;

import com.example.univerproject.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

}
package com.example.cs203g1t3.repository;

import com.example.cs203g1t3.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    
}

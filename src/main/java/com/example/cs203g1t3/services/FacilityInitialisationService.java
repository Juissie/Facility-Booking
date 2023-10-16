package com.example.cs203g1t3.services;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cs203g1t3.models.Facility;
import com.example.cs203g1t3.repository.FacilityRepository;

@Service
public class FacilityInitialisationService {
    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityInitialisationService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void initialiseFacilities() {
        Facility badminton = new Facility("Badminton Court", "Opens from 10am to 6pm", LocalTime.of(9, 0), LocalTime.of(18, 0) );
        // LocalTime openTime = LocalTime.of(9, 0);
        // LocalTime closingTime = LocalTime.of(18, 0);
        // badminton.setOpenTime(openTime);
        // badminton.setClosingTime(closingTime);
        badminton.calculateTimeSlots();
        facilityRepository.save(badminton);
    }
}

package com.example.cs203g1t3.controller;

import com.example.cs203g1t3.models.Facility;
import com.example.cs203g1t3.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import java.util.List;
import javax.validation.Valid;

import com.example.cs203g1t3.exceptions.FacilityNotFoundException;
import com.example.cs203g1t3.models.Booking;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/facilities")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createFacility(@Valid @RequestBody Facility facility) {
        Facility newFacility = facilityService.createFacility(facility);
        return new ResponseEntity<>(newFacility, HttpStatus.CREATED);
    }    

    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(facilities);
    }

    /**
     * Search for facility with the given id
     * If there is no facility with the given "id", throw a FacilityNotFoundException
     * @param facilityId
     * @return facility with the given facilityId
     */
    @GetMapping("/{facilityId}")
    public ResponseEntity<?> getFacilityById(@PathVariable Long facilityId) {
        Facility facility = facilityService.getFacility(facilityId);
        if (facility == null) {
            throw new FacilityNotFoundException(facilityId);
        }
        return ResponseEntity.ok(facility);
    }

    @PutMapping("/{facilityId}")
    @PreAuthorize("hasRole('ADMIN')") // Example: Only admins can update facilities
    public ResponseEntity<?> updateFacility(@PathVariable Long facilityId, @Valid @RequestBody Facility newFacilityInfo) {
        Facility facility = facilityService.updateFacility(facilityId, newFacilityInfo);
        if (facility != null) {
            throw new FacilityNotFoundException(facilityId);
        } else {
            throw new FacilityNotFoundException(facilityId);
        }
    }

    @DeleteMapping("/{facilityId}")
    @PreAuthorize("hasRole('ADMIN')") // Example: Only admins can delete facilities
    public ResponseEntity<?> deleteFacility(@PathVariable Long facilityId) {
        try {   
            facilityService.deleteFacility(facilityId);
        } catch(EmptyResultDataAccessException e) {
            throw new FacilityNotFoundException(facilityId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping("/{facilityId}")
    // public ResponseEntity<?> updateFacilityTiming(@PathVariable Long facilityId, @RequestBody Booking booking){
    //     Facility facility = facilityService.updFacilityTiming(facilityId, booking);
    //     if (facility == null) {
    //         return new ResponseEntity<>("Facility not found", HttpStatus.NOT_FOUND);
    //     }
    //     return ResponseEntity.ok(facility);

    // }
}

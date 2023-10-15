package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.Facility;
import com.example.cs203g1t3.models.Booking;
import com.example.cs203g1t3.repository.BookingRepository;
import com.example.cs203g1t3.repository.FacilityRepository;

import java.time.LocalTime;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    private FacilityRepository facilities;

    public FacilityService(FacilityRepository facilities) {
        this.facilities = facilities;
    }

    public List<Facility> listFacilities() {
        return facilities.findAll();
    }

    public Facility getFacility(Long facilityId) {
        return facilities.findById(facilityId).orElse(null);
    }

    public List<Facility> getAllFacilities() {
        return facilities.findAll();
    }

    public Facility createFacility(Facility facility) {
        return facilities.save(facility);
    }


    public Facility updateFacility(Long facilityId, Facility newFacility) {
        return facilities.findById(facilityId).map(facility -> {
                facility.setFacilityType(newFacility.getFacilityType());
                facility.setDescription(newFacility.getDescription());
                facility.setOpenTime(newFacility.getOpenTime());
                facility.setClosingTime(newFacility.getClosingTime());
                return facilities.save(facility);
            }).orElse(null);
    }

    public void deleteFacility(Long facilityId) {
        facilities.deleteById(facilityId);
    }

    public List<Booking> getAllBookingsByFacility(Long facilityId) {
        Facility facility = facilities.findById(facilityId).orElse(null);
        if (facility == null) {
            return null;
        }
        return facility.getBookings();

    }

    // First implementation
    public List<LocalTime> getAvailableTimeSlots(Facility facility) {
        if (facility != null) {
            List<LocalTime> allTimeSlots = facility.getTimeSlots();
            List<Booking> bookedBookings = facility.getBookings();
        
            List<LocalTime> availableTimeSlots = new ArrayList<>(allTimeSlots);
            for (Booking booking : bookedBookings) {
                LocalTime start = booking.getStartTime();
                LocalTime end = booking.getEndTime();
                Set<LocalTime> bookingTimes = new HashSet<>();
                while(start.isBefore(end)){
                    bookingTimes.add(start);
                    start = start.plusMinutes(30);
                }
                for (LocalTime time:bookingTimes){
                    availableTimeSlots.remove(time);
                }
            }
            return availableTimeSlots;
        }
        return null;
    
    }

    // Second implementation
    public void updateTimeSlot(Long facilityId, Booking booking){
        Facility facility = facilities.findById(facilityId).orElse(null);
        if (facility != null) {
            LocalTime start = booking.getStartTime();
            LocalTime end = booking.getEndTime();

            Set<LocalTime> bookingTimes = new HashSet<>();
            while(start.isBefore(end)){
                bookingTimes.add(start);
                start = start.plusMinutes(30);
            }
            for(LocalTime time:bookingTimes){
                facility.getTimeSlots().remove(time);
            }
        }
    }


}

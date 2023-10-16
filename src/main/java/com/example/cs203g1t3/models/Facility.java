package com.example.cs203g1t3.models;

import java.time.LocalTime;
import java.util.*;

import org.springframework.cglib.core.Local;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "facility")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

// LocalDateTime reference : 
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html


public class Facility {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long facilityId;
    private String facilityType;
    private String description;
    @Column(columnDefinition = "TIME")
    private LocalTime openTime;
    @Column(columnDefinition = "TIME")
    private LocalTime closingTime;
    private List<LocalTime> timeSlots;


    @OneToMany(mappedBy = "facility", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Booking> bookings;

    
    // private boolean isCurrentlyBooked;


    public Facility(String facilityType, String description) {
        this.facilityType = facilityType;
        this.description = description;
    }

    public Facility(String facilityType, String description, LocalTime openTime, LocalTime closingTime) {
        this.facilityType = facilityType;
        this.description = description;
        this.openTime = openTime;
        this.closingTime = closingTime;
        //hardcoded timings for testing 
        // openTime = LocalTime.of(8, 0, 0);
        // endTime = LocalTime.of(10, 0, 0);
        calculateTimeSlots();
    }

    public int calculateTimeSlots(){
        //takes the opentime and endtime of the the facility and breaks them up into timeslots of 30min each
        //returns the number of timeslots
        if(openTime == null || closingTime == null){
            return 0;
        }
        timeSlots = new ArrayList<>();
        LocalTime tempOpen = openTime;
        LocalTime tempEnd = closingTime;

        while(tempOpen.isBefore(tempEnd)){
            timeSlots.add(tempOpen);
            tempOpen = tempOpen.plusMinutes(30);
        }
        return timeSlots.size();
    }

    // Moved to FacilityService
    // public void updateTimeSlot(Booking booking){
    //     LocalTime start = booking.getStartTime();
    //     LocalTime end = booking.getEndTime();

    //     List<LocalTime> bookingTimes = new ArrayList<>();
    //     while(start.isBefore(end)){
    //         bookingTimes.add(start);
    //         start = start.plusMinutes(30);
    //     }
    //     for(LocalTime time:bookingTimes){
    //         timeSlots.remove(time);
    //     }
    // }
}

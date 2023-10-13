package com.example.cs203g1t3.models;

import java.time.LocalTime;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(columnDefinition = "TIME")
    private LocalTime openTime;
    @Column(columnDefinition = "TIME")
    private LocalTime endTime;

    private List<LocalTime> timeSlots;

    private boolean isCurrentlyBooked;
    private String description;

    public Facility(String facilityType, String description) {
        this.facilityType = facilityType;
        this.description = description;
    }

    public int calculateTimeSlots(){
        //takes the opentime and endtime of the the facility and breaks them up into timeslots of 30min each
        //returns the number of timeslots
        timeSlots = new ArrayList<>();
        LocalTime tempOpen = openTime;
        LocalTime tempEnd = endTime;

        while(tempOpen.isBefore(tempEnd)){
            timeSlots.add(tempOpen);
            tempOpen = tempOpen.plusMinutes(30);
        }
        return timeSlots.size();
    }
}

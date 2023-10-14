package com.example.cs203g1t3.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "booking")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Booking {

    @Id
    @GeneratedValue
    private Long bookingId;

    @Column(columnDefinition = "TIME")
    private LocalTime openTime;
    @Column(columnDefinition = "TIME")
    private LocalTime endTime;

    private LocalDate dateCreated;


    
    //BookingStatus
    // private BookingStatus bookingStatus;
}

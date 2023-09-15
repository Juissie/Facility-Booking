package com.example.cs203g1t3.repository;

import com.example.cs203g1t3.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    
}

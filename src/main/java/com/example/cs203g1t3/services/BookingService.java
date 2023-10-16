package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.Booking;
import com.example.cs203g1t3.models.Facility;
import com.example.cs203g1t3.repository.BookingRepository;

import java.time.LocalTime;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookings;

    public BookingService(BookingRepository bookings) {
        this.bookings =  bookings;
    }

    public List<Booking> listBookings() {
        return bookings.findAll();
    }

    public Booking getBooking(Long bookingId) {
        return bookings.findById(bookingId).orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookings.findAll();
    }

    public Booking createBooking(Booking booking) {
        return bookings.save(booking);
    }

    public Booking updateBooking(Long bookingId, Booking newBooking) {
        return bookings.findById(bookingId).map(booking -> {
            booking.setStartTime(newBooking.getStartTime());
            booking.setEndTime(newBooking.getEndTime());
            return bookings.save(booking);
        }).orElse(null);
    }

    public void deleteBooking(Long bookingId) {
        bookings.deleteById(bookingId);
    }

    public boolean isValidBooking(LocalTime startTime, LocalTime endTime, Facility facility) {
        List<LocalTime> timeSlots = facility.getTimeSlots();
        return timeSlots.contains(startTime) && timeSlots.contains(endTime);
    }
    
}

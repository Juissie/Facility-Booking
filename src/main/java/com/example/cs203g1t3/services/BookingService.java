package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.Booking;
import com.example.cs203g1t3.models.Facility;
import com.example.cs203g1t3.repository.BookingRepository;
import com.example.cs203g1t3.repository.FacilityRepository;

import java.time.LocalTime;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookings;
    private FacilityRepository facilityRepo;

    public BookingService(BookingRepository bookings, FacilityRepository facility) {
        this.bookings =  bookings;
        this.facilityRepo = facility;
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

    public Booking updateBooking(Long facilityId,Long bookingId, Booking newBooking) {
        //idea of this method
        //get old booking -> remove bookings from current booking list
        //

        Booking booking = bookings.findById(bookingId).orElse(null);
        //scuffed code
        if(booking == null){      //if booking not found
            return null;
        }

        List<Booking> allBookings = getAllBookings();
        allBookings.remove(booking);                //remove current booking from the list

        List<LocalTime> timingTaken = new ArrayList<>();   //list of all timings that is taken

        for(Booking tempBooking: allBookings){
            List<LocalTime> tempList = listBookingTimings(tempBooking);
            timingTaken.addAll(tempList);
        }

        List<LocalTime> newBookingTiming = listBookingTimings(newBooking);

        //   !Collections.disjoint(list1, list2) returns true if there is overlap
        if(!Collections.disjoint(timingTaken, newBookingTiming)){
            return null;
        }

        

        Facility facility = facilityRepo.findById(facilityId).orElse(null);

        List<LocalTime> facilityTimes = facility.getTimeSlots();
        facilityTimes.addAll(listBookingTimings(booking));
        facilityTimes.removeAll(newBookingTiming);
        Collections.sort(facilityTimes);

        facility.setTimeSlots(facilityTimes);
        facilityRepo.save(facility);

        booking.setStartTime(newBooking.getStartTime());
        booking.setEndTime(newBooking.getEndTime());
        bookings.save(booking);
        return booking;

        //old impl
        // return bookings.findById(bookingId).map(booking -> {
        //     booking.setStartTime(newBooking.getStartTime());
        //     booking.setEndTime(newBooking.getEndTime());
        //     return bookings.save(booking);
        // }).orElse(null);
    }

    public void deleteBooking(Long bookingId) {
        bookings.deleteById(bookingId);
    }

    public boolean isValidBooking(LocalTime startTime, LocalTime endTime, Facility facility) {
        if(startTime.isAfter(endTime)){          //checking if startTime is after endTime
            return false;
        }
        List<LocalTime> allAvailableTimings = facility.getTimeSlots();


        //check if each 30 minute slot starting from start time is 
        //available in the time available time slot in facility until the end time
        while(startTime.isBefore(endTime)){
            if(!allAvailableTimings.contains(startTime)){
                return false;
            }

            startTime = startTime.plusMinutes(30);
        }
        return true;

        //old impl
        // List<LocalTime> timeSlots = facility.getTimeSlots();
        // return timeSlots.contains(startTime) && timeSlots.contains(endTime);
    }
    

    public List<LocalTime> listBookingTimings(Booking booking){
        List<LocalTime> list = new ArrayList<>();
        LocalTime startTime = booking.getStartTime();
        LocalTime endTime = booking.getEndTime();

        while(startTime.isBefore(endTime)){
            list.add(startTime);
            startTime = startTime.plusMinutes(30);
        }
        return list;
    }
}

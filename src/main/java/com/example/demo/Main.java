package com.example.demo;

import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.PropertyReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @Autowired
    public static PropertyReservationRepository propertyReservationRepository;

    @Autowired
    public static GuestRepository guestRepository;

    @Autowired
    public static PropertyRepository propertyRepository;

    public static void main(String[] args) {

        List<String> stats = new ArrayList<>();

        guestRepository.findAll().forEach(guest -> {
            int Id = guest.getId();
            String guestFirstName = guest.getFirstName();
            String guestLastName = guest.getLastName();
            //int numOfReservation = 0;
            propertyReservationRepository.findAll().forEach(reservation ->{
                //int numOfReservation = 0;
                if(guest.getId() == reservation.getGuest().getId()){
                    //numOfReservation ++;
                }
                //return numOfReservation;
            });
            stats.add("Guest ID :" + Id + " First Name: " + guestFirstName + " Last Name: " + guestLastName + " Number of Reservation: " );
        });

        System.out.println(stats);
    }
}

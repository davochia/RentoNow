package com.example.demo.repository;

import com.example.demo.model.PropertyReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, Integer> {

//    @Query(value = "SELECT * FROM Reservation r WHERE r.property_id = ?1", nativeQuery = true)
//    List<PropertyReservation> propertyReservations(Integer property_id);

}

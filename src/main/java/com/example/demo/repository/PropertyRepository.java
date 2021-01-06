package com.example.demo.repository;

import com.example.demo.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Property p SET p.images = :path WHERE p.id = :id")
    void saveImageToProperty(@Param("path") String path, @Param("id") Integer id);
}

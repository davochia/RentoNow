package com.example.demo.repository;

import com.example.demo.model.ImageDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDBRepository extends JpaRepository<ImageDB, Integer> {
}

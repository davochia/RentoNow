package com.rentonow.rentonow.repository;

import com.rentonow.rentonow.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends JpaRepository<Administrator, Long> {
}

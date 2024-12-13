package com.polideportivo.backend_springboot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polideportivo.backend_springboot.domain.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    boolean existsByEmail(String email);
}

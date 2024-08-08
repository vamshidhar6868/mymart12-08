package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.NavLink;

public interface NavLinkRepository extends JpaRepository<NavLink, Long> {
    // Add custom queries if needed
}
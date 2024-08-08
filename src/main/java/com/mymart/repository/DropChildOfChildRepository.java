package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.DropChildOfChild;

public interface DropChildOfChildRepository extends JpaRepository<DropChildOfChild, Long> {
    List<DropChildOfChild> findByDropChildId(Long dropChildId);
    
}

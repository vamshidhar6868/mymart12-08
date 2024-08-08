package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Integer> {

}

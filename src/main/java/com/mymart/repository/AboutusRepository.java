package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Aboutus;

@Repository
public interface AboutusRepository extends JpaRepository<Aboutus, Long>{

}

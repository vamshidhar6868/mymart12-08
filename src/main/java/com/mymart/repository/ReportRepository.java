package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	List<Report> findByEmail(String email);
   
}
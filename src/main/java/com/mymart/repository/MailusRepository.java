package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Mailus;

@Repository
public interface MailusRepository extends JpaRepository<Mailus, Long> {

}

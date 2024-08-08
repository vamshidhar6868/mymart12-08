package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Consumerpolicy;
@Repository
public interface ConsumerpolicyRepository extends JpaRepository<Consumerpolicy, Long>{

}

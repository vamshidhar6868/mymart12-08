package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Roaddress;
@Repository
public interface RoaddressRepository extends JpaRepository<Roaddress, Long> {

}

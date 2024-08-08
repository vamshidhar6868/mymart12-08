package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Keepintouch;
@Repository
public interface KeepintouchRepository extends JpaRepository<Keepintouch, Long>{

}

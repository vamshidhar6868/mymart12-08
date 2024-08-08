package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Downloadapps;
@Repository
public interface DownloadappsRepository extends JpaRepository<Downloadapps, Long>{

}

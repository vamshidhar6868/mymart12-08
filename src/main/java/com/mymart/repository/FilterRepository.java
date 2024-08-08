package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.Filter;


@Repository
public interface FilterRepository extends JpaRepository<Filter,Integer>
{


}
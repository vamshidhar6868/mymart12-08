package com.mymart.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.Faq;

public interface FaqRepository extends JpaRepository<Faq,Long>{
    Optional<Faq> findById(Long id);
    void deleteById(Long id);
	

}

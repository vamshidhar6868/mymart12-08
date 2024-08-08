package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.MovingCards;

public interface MovingCardsRepository extends JpaRepository<MovingCards, Integer>
{

}

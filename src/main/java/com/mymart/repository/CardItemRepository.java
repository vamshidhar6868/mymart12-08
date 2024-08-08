package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.CardItem;



public interface CardItemRepository extends JpaRepository<CardItem, Integer> {
}

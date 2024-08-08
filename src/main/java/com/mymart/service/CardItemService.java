package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.CardItem;
import com.mymart.repository.CardItemRepository;



@Service
public class CardItemService {

    private final CardItemRepository cardItemRepository;

    @Autowired
    public CardItemService(CardItemRepository cardItemRepository) {
        this.cardItemRepository = cardItemRepository;
    }

    public List<CardItem> getAllCardItems() {
        return cardItemRepository.findAll();
    }

}

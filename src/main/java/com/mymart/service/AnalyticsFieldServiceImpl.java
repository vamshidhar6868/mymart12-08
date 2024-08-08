package com.mymart.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.AnalyticsField;
import com.mymart.repository.AnalyticsFieldRepository;

@Service
public class AnalyticsFieldServiceImpl implements AnalyticsFieldService{
	@Autowired
    private AnalyticsFieldRepository analyticsFieldRepository;

    @Override
    public List<AnalyticsField> getAllAnalyticsFields() {
        return analyticsFieldRepository.findAll();
    }

    @Override
    public AnalyticsField getAnalyticsFieldById(Long id) {
        return analyticsFieldRepository.findById(id).orElse(null);
    }

    @Override
    public AnalyticsField createAnalyticsField(AnalyticsField analyticsField) {
        return analyticsFieldRepository.save(analyticsField);
    }

    @Override
    public AnalyticsField updateAnalyticsField(Long id, AnalyticsField analyticsField) {
        if (analyticsFieldRepository.existsById(id)) {
            analyticsField.setId(id);
            return analyticsFieldRepository.save(analyticsField);
        }
        return null;
    }

    @Override
    public void deleteAnalyticsField(Long id) {
        analyticsFieldRepository.deleteById(id);
    }
}





package com.mymart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymart.model.AnalyticsField;
import com.mymart.service.AnalyticsFieldService;

@Controller
public class AnalyticsFieldController {

    private final AnalyticsFieldService analyticsFieldService;
      
    @Autowired
    public AnalyticsFieldController(AnalyticsFieldService analyticsFieldService) {
        this.analyticsFieldService = analyticsFieldService;
    }

    @GetMapping("/analytics")
    public String showAnalyticsPage(Model model) {
        List<AnalyticsField> fields = analyticsFieldService.getAllAnalyticsFields();
        model.addAttribute("fields", fields);
        return "analytics";
    }
    @GetMapping("/analytics/{id}")
    public String showAnalyticsFieldDetails(@PathVariable Long id, Model model) {
        AnalyticsField field = analyticsFieldService.getAnalyticsFieldById(id);
        model.addAttribute("field", field);
        return "analytics_details";
    }

    @GetMapping("/analytics/new")
    public String showNewAnalyticsFieldForm(Model model) {
        model.addAttribute("field", new AnalyticsField());
        return "new_analytics";
    }

    @PostMapping("/analytics")
    public String createAnalyticsField(@ModelAttribute("field") AnalyticsField field) {
        analyticsFieldService.createAnalyticsField(field);
        return "redirect:/analytics";
    }

    @GetMapping("/analytics/edit/{id}")
    public String showEditAnalyticsFieldForm(@PathVariable Long id, Model model) {
        AnalyticsField field = analyticsFieldService.getAnalyticsFieldById(id);
        model.addAttribute("field", field);
        return "edit_analytics";
    }

    @PostMapping("/analytics/update/{id}")
    public String updateAnalyticsField(@PathVariable Long id, @ModelAttribute("field") AnalyticsField field) {
        analyticsFieldService.updateAnalyticsField(id, field);
        return "redirect:/analytics";
    }

    @GetMapping("/analytics/delete/{id}")
    public String deleteAnalyticsField(@PathVariable Long id) {
        analyticsFieldService.deleteAnalyticsField(id);
        return "redirect:/analytics";
    }
}


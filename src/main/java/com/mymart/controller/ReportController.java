package com.mymart.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mymart.model.Report;
import com.mymart.repository.ReportRepository;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
    private ReportRepository repo;

    
    
    @PostMapping("/reportform")
    public String submitReport(@ModelAttribute("report") Report report, RedirectAttributes redirectAttributes) {
        repo.save(report);
        redirectAttributes.addFlashAttribute("successMessage", "Submitted successfully!");
        return "redirect:/Contact";   
    }

}


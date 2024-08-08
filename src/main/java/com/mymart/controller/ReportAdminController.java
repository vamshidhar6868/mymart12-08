package com.mymart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mymart.model.ReportAdmin;
import com.mymart.repository.ReportAdminRepository;

@Controller
@RequestMapping("/reportadmin")
public class ReportAdminController {

    @Autowired
    private ReportAdminRepository repo;

    @GetMapping({ "", "/" })
    public String showAdminReports(Model model) {
        List<ReportAdmin> reports = repo.findAll();
        model.addAttribute("reports", reports);
        return "reportadmin/readreports";
    }
    
    
}

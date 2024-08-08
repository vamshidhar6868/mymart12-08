package com.mymart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mymart.model.DropChild;
import com.mymart.model.DropChildOfChild;
import com.mymart.model.DropdownItem;
import com.mymart.model.GrandChild;
import com.mymart.model.NavLink;
import com.mymart.repository.DropChildOfChildRepository;
import com.mymart.repository.DropChildRepository;
import com.mymart.repository.DropdownItemRepository;
import com.mymart.repository.GrandChildRepository;
import com.mymart.repository.NavLinkRepository;
import com.mymart.service.NavBarService;

@Controller
@RequestMapping("/Admin")
public class AdminNavbarController {
private final NavBarService service; 
	
    public AdminNavbarController(NavBarService service) {
        this.service = service;
    }
    @Autowired
    NavLinkRepository navrepo;
    
    @Autowired
    DropdownItemRepository droprepo;
    
    @Autowired
    DropChildRepository dropCrepo;
    
    @Autowired
    DropChildOfChildRepository dropCchildrepo;
    
    @Autowired
    GrandChildRepository grandCrepo;
    

	@GetMapping("/Navbar")
    public String getAllNavbarData(Model model) {
        List<NavLink> allNavLinks = service.getAllNavLinks();
        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
        model.addAttribute("allNavLinks", allNavLinks);
        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);

        return "Navbar/Navbar"; 
    }
	@GetMapping("/addNavbar")
	public String showAddForm(Model model) {
		model.addAttribute("nav", new NavLink());
		return "Navbar/addNavbar";
	}
	@PostMapping("/addNavbar")
    public String addNavBar(@ModelAttribute("nav")NavLink nav) {
    	System.out.println(nav);
   
    	navrepo.save(nav);
    	
    	return "redirect:/Admin/Navbar";
    }
	@GetMapping("/editNavbar/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        NavLink nav = navrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("nav", nav);
        return "Navbar/editNavbar";
    }
    
    @PostMapping("/editNavbar/{id}")
    public String updateNavbar(@PathVariable long id, @ModelAttribute("nav") NavLink nav) {
  
    	nav.setId(id);
       navrepo.save(nav);
       return "redirect:/Admin/Navbar";
   }
    
    @GetMapping("/deleteNavbar/{id}")
    public String deleteNavbar(@PathVariable("id") long id) {
    	NavLink nav = navrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        navrepo.delete(nav);
        return "redirect:/Admin/Navbar";
    }
	
	@GetMapping("/addDropdown")
	public String showAddDrop(Model model) {
		model.addAttribute("drop", new DropdownItem());
		return "Navbar/addDropdown";
	}

	
	@PostMapping("/addDropdown")
	public String addDropdown(@ModelAttribute DropdownItem drop) {
       
        droprepo.save(drop);
        return "redirect:/Admin/Navbar";
    }
	
	@GetMapping("/editDropdown/{id}")
    public String showEditFormdrop(@PathVariable("id") long id, Model model) {
        DropdownItem drop = droprepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("drop", drop);
        return "Navbar/editDropdown";
    }
    
    @PostMapping("/editDropdown/{id}")
    public String updateDropdown(@PathVariable long id, @ModelAttribute("drop") DropdownItem drop) {
  
    	drop.setId(id);
       droprepo.save(drop);
       return "redirect:/Admin/Navbar";
   }
    
    @GetMapping("/deleteDropdown/{id}")
    public String deleteDropdown(@PathVariable("id") long id) {
    	DropdownItem drop = droprepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        droprepo.delete(drop);
        return "redirect:/Admin/Navbar";
    }
	
	
	@GetMapping("/addDropChild")
	public String showAddDropChild(Model model) {
		model.addAttribute("dropChild", new DropChild());
		return "Navbar/addDropChild";
	}

	
	@PostMapping("/addDropChild")
	public String addDropChild(@ModelAttribute DropChild dropChild) {
       
        dropCrepo.save(dropChild);
        return "redirect:/Admin/Navbar";
    }
	
	
	@GetMapping("/editDropChild/{id}")
    public String showEditFormdropChild(@PathVariable("id") long id, Model model) {
        DropChild dropChild = dropCrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("dropChild", dropChild);
        return "Navbar/editDropChild";
    }
    
    @PostMapping("/editDropChild/{id}")
    public String updateDropChild(@PathVariable long id, @ModelAttribute("dropChild") DropChild dropChild) {
  
    	dropChild.setId(id);
       dropCrepo.save(dropChild);
       return "redirect:/Admin/Navbar";
   }
    
    @GetMapping("/deleteDropChild/{id}")
    public String deleteDropChild(@PathVariable("id") long id) {
    	DropChild dropChild = dropCrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        dropCrepo.delete(dropChild);
        return "redirect:/Admin/Navbar";
    }
	
	
    
    
	
	
	@GetMapping("/addDropChildOfChild")
	public String showAddDropChildOfChild(Model model) {
		model.addAttribute("dropChildOfChild", new DropChildOfChild());
		return "Navbar/addDropChildOfChild";
	}

	
	@PostMapping("/addDropChildOfChild")
	public String addDropChild(@ModelAttribute DropChildOfChild dropChildOfChild) {
       
        dropCchildrepo.save(dropChildOfChild);
        return "redirect:/Admin/Navbar";
    }
	
	@GetMapping("/editChildOfChild/{id}")
    public String showEditFormdropChildOfChild(@PathVariable("id") long id, Model model) {
        DropChildOfChild dropOfChild = dropCchildrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("dropOfChild", dropOfChild);
        return "Navbar/editChildOfChild";
    }
    
    @PostMapping("/editChildOfChild/{id}")
    public String updateDropChildOfChild(@PathVariable long id, @ModelAttribute("dropOfChild") DropChildOfChild dropOfChild) {
  
    	dropOfChild.setId(id);
       dropCchildrepo.save(dropOfChild);
       return "redirect:/Admin/Navbar";
   }
    
    @GetMapping("/deleteChildOfChild/{id}")
    public String deleteDropChildOfChild(@PathVariable("id") long id) {
    	DropChildOfChild dropOfChild = dropCchildrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        dropCchildrepo.delete(dropOfChild);
        return "redirect:/Admin/Navbar";
    }
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/addGrandChild")
	public String showAddGrandChild(Model model) {
		model.addAttribute("grandChild", new GrandChild());
		return "Navbar/addGrandChild";
	}

	
	@PostMapping("/addGrandChild")
	public String addGrandChild(@ModelAttribute GrandChild grandChild) {
       
        grandCrepo.save(grandChild);
        return "redirect:/Admin/Navbar";
    }
	@GetMapping("/editGrandChild/{id}")
    public String showEditFormGrandChild(@PathVariable("id") long id, Model model) {
        GrandChild grandChild = grandCrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("grandChild", grandChild);
        return "Navbar/editGrandChild";
    }
    
    @PostMapping("/editGrandChild/{id}")
    public String updateGrandChild(@PathVariable long id, @ModelAttribute("grandChild") GrandChild grandChild) {
  
    	grandChild.setId(id);
       grandCrepo.save(grandChild);
       return "redirect:/Admin/Navbar";
   }
    
    @GetMapping("/deleteGrandChild/{id}")
    public String deleteGrandChild(@PathVariable("id") long id) {
    	GrandChild grandChild = grandCrepo.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        grandCrepo.delete(grandChild);
        return "redirect:/Admin/Navbar";
    }
    @GetMapping("/About")
    public String getAbout(Model model) {
        List<NavLink> allNavLinks = service.getAllNavLinks();
        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
        model.addAttribute("allNavLinks", allNavLinks);
        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);

        return "Navbar/Adminabout";
    }

}

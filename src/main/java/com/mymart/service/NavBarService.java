package com.mymart.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


@Service
public class NavBarService {

	@Autowired
    private NavLinkRepository navLinkRepository;

    @Autowired
    private DropdownItemRepository dropdownItemRepository;

    @Autowired
    private DropChildRepository dropChildRepository;

    @Autowired
    private DropChildOfChildRepository dropChildOfChildRepository;

    @Autowired
    private GrandChildRepository grandChildRepository;

    public List<NavLink> getAllNavLinks() {
        return navLinkRepository.findAll();
    }

    public List<DropdownItem> getDropdownItemsByNavbarItemId(Long navbarItemId) {
        return dropdownItemRepository.findByNavLinkId(navbarItemId);
    }

    public List<DropChild> getDropChildrenByDropdownItemId(Long dropdownItemId) {
        return dropChildRepository.findByDropdownItemId(dropdownItemId);
    }

    public List<DropChildOfChild> getDropChildrenOfChildByDropChildId(Long dropChildId) {
        return dropChildOfChildRepository.findByDropChildId(dropChildId);
    }

    public List<GrandChild> getGrandChildrenByDropChildOfChildId(Long dropChildOfChildId) {
        return grandChildRepository.findByDropChildOfChildId(dropChildOfChildId);
    }

   
    public Map<NavLink, List<DropdownItem>> getNavbarWithDropdownData() {
        List<NavLink> navbarItems = navLinkRepository.findAll();
        Map<NavLink, List<DropdownItem>> dropdownData = new LinkedHashMap<>();

        for (NavLink navbarItem : navbarItems) {
            List<DropdownItem> dropdownItems = dropdownItemRepository.findByNavLinkId(navbarItem.getId());
            
            // Iterate over dropdown items and set DropChildren
            for (DropdownItem dropdownItem : dropdownItems) {
                List<DropChild> dropChildren = dropChildRepository.findByDropdownItemId(dropdownItem.getId());
                
                // Iterate over DropChild items and set DropChildrenOfChild
                for (DropChild dropChild : dropChildren) {
                    List<DropChildOfChild> dropChildrenOfChild = dropChildOfChildRepository.findByDropChildId(dropChild.getId());
                    
                    // Iterate over DropChildOfChild items and set GrandChildren
                    for (DropChildOfChild dropChildOfChild : dropChildrenOfChild) {
                        List<GrandChild> grandChildren = grandChildRepository.findByDropChildOfChildId(dropChildOfChild.getId());
                        dropChildOfChild.setGrandChildren(grandChildren);
                    }
                    
                    dropChild.setDropChildrenOfChild(dropChildrenOfChild);
                }
                
                dropdownItem.setDropChildren(dropChildren);
            }
            
            dropdownData.put(navbarItem, dropdownItems);
        }

        return dropdownData;
    }
    
}

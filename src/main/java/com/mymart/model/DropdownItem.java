package com.mymart.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class DropdownItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @ManyToOne
    @JoinColumn(name = "nav_link_id")
    private NavLink navLink;
    
    @OneToMany(mappedBy = "dropdownItem")
    private List<DropChild> dropChildren;
   
   
    public Long getNavbarItemId() {
        // Return the appropriate field value or perform any logic to get the ID
        return navLink.getId();
    }
    
    

	public List<DropChild> getDropChildren() {
		return dropChildren;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public NavLink getNavLink() {
		return navLink;
	}

	public void setNavLink(NavLink navLink) {
		this.navLink = navLink;
	}

	@Override
	public String toString() {
		return "DropdownItem [id=" + id + ", itemName=" + itemName + ", navLink=" + navLink + "]";
	}

	public DropdownItem(Long id, String itemName, NavLink navLink) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.navLink = navLink;
	}

	public DropdownItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setDropChildren(List<DropChild> dropChildren) {
        this.dropChildren = dropChildren;
    }

	

    
}
package com.mymart.model;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class NavLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    
    @OneToMany(mappedBy = "navLink")
    private List<DropdownItem> dropdownItems;
    
    
	public List<DropdownItem> getDropdownItems() {
		return dropdownItems;
	}

	public void setDropdownItems(List<DropdownItem> dropdownItems) {
		this.dropdownItems = dropdownItems;
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

	@Override
	public String toString() {
		return "NavLink [id=" + id + ", itemName=" + itemName + "]";
	}

	public NavLink(Long id, String itemName) {
		super();
		this.id = id;
		this.itemName = itemName;
	}

	public NavLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

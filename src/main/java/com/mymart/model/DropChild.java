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
public class DropChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @ManyToOne
    @JoinColumn(name = "dropdown_item_id")
    private DropdownItem dropdownItem;
    
    @OneToMany(mappedBy = "dropChild")
    private List<DropChildOfChild> dropChildrenOfChild;
    
    

	public List<DropChildOfChild> getDropChildrenOfChild() {
		return dropChildrenOfChild;
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

	public DropdownItem getDropdownItem() {
		return dropdownItem;
	}

	public void setDropdownItem(DropdownItem dropdownItem) {
		this.dropdownItem = dropdownItem;
	}

	@Override
	public String toString() {
		return "DropChild [id=" + id + ", itemName=" + itemName + ", dropdownItem=" + dropdownItem + "]";
	}

	public DropChild(Long id, String itemName, DropdownItem dropdownItem) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.dropdownItem = dropdownItem;
	}

	public DropChild() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setDropChildrenOfChild(List<DropChildOfChild> dropChildrenOfChild) {
        this.dropChildrenOfChild = dropChildrenOfChild;
    }

    
}
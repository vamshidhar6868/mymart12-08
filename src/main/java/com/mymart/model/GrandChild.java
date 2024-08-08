package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class GrandChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @ManyToOne
    @JoinColumn(name = "drop_child_of_child_id")
    private DropChildOfChild dropChildOfChild;

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

	public DropChildOfChild getDropChildOfChild() {
		return dropChildOfChild;
	}

	public void setDropChildOfChild(DropChildOfChild dropChildOfChild) {
		this.dropChildOfChild = dropChildOfChild;
	}

	@Override
	public String toString() {
		return "GrandChild [id=" + id + ", itemName=" + itemName + ", dropChildOfChild=" + dropChildOfChild + "]";
	}

	public GrandChild(Long id, String itemName, DropChildOfChild dropChildOfChild) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.dropChildOfChild = dropChildOfChild;
	}

	public GrandChild() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}

package com.mymart.model;

import java.util.List;

import jakarta.persistence.Column;
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
public class DropChildOfChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "drop_child_id")
    private DropChild dropChild;
    
    @OneToMany(mappedBy = "dropChildOfChild")
    private List<GrandChild> grandChildren;
    
   
    

	public List<GrandChild> getGrandChildren() {
		return grandChildren;
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

	public DropChild getDropChild() {
		return dropChild;
	}

	public void setDropChild(DropChild dropChild) {
		this.dropChild = dropChild;
	}

	@Override
	public String toString() {
		return "DropChildOfChild [id=" + id + ", itemName=" + itemName + ", dropChild=" + dropChild + "]";
	}

	public DropChildOfChild(Long id, String itemName, DropChild dropChild) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.dropChild = dropChild;
	}

	public DropChildOfChild() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setGrandChildren(List<GrandChild> grandChildren) {
        this.grandChildren = grandChildren;
    }
}

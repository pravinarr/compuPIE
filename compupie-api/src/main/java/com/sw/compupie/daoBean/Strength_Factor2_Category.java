package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class Strength_Factor2_Category {
	
	private int id;
	
	private String category;
	
	private List<Strength_Factor2_Problems> items = new ArrayList<Strength_Factor2_Problems>();

	public int getId() {
		return id;
	} 

	public String getCategory() {
		return category;
	}
	
	public List<Strength_Factor2_Problems> getItems() {
		return items;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public void setItems(List<Strength_Factor2_Problems> items) {
		this.items = items;
	}
}

package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class PhysicalHealthProblems {
	
	private int id;
	
	private String category;
	
	private List<PhysicalHealthItem> items = new ArrayList<PhysicalHealthItem>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<PhysicalHealthItem> getItems() {
		return items;
	}

	public void setItems(List<PhysicalHealthItem> items) {
		this.items = items;
	}
	
}

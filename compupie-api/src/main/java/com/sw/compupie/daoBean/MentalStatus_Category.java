package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class MentalStatus_Category {
	
	private int id;
	
	private String category;
	
	private List<MentalStatusProblemsBean> items = new ArrayList<MentalStatusProblemsBean>();

	public int getId() {
		return id;
	} 

	public String getCategory() {
		return category;
	}
	
	public List<MentalStatusProblemsBean> getItems() {
		return items;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public void setItems(List<MentalStatusProblemsBean> items) {
		this.items = items;
	}
}


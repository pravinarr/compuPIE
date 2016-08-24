package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class SocialRoleCategory {
	
	private int id;
	
	private String category;
	
	private List<SocialRoleProblems> items = new ArrayList<SocialRoleProblems>();

	public int getId() {
		return id;
	} 

	public String getCategory() {
		return category;
	}
	
	public List<SocialRoleProblems> getItems() {
		return items;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public void setItems(List<SocialRoleProblems> items) {
		this.items = items;
	}
}

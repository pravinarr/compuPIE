package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class Priority {
	
	private int id;
	
	private String P_Category;
	
	private List<Priority> items = new ArrayList<Priority>();
	
	public int getId(){
		return id;
	}
	
	public String getCategory(){
		return P_Category;
	}
	
	public List<Priority> getItems() {
		return items;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String setCategory(String P_Category){
		return this.P_Category = P_Category;
	}
	
	public void setItems(List<Priority> items){
		this.items = items;
	}
	
}


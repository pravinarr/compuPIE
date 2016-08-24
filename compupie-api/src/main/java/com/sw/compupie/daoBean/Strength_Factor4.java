package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class Strength_Factor4 {
	
	private int id;
	
	private String SF4_Category;
	
	private List<Strength_Factor4> items = new ArrayList<Strength_Factor4>();
	
	public int getId(){
		return id;
	}
	
	public String getCategory(){
		return SF4_Category;
	}
	
	public List<Strength_Factor4> getItems() {
		return items;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String setCategory(String SF4_Category){
		return this.SF4_Category = SF4_Category;
	}
	
	public void setItems(List<Strength_Factor4> items){
		this.items = items;
	}
	
}


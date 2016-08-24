package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class Coping_Ability {
	
	private int id;
	
	private String D_Category;
	
	private List<Coping_Ability> items = new ArrayList<Coping_Ability>();
	
	public int getId(){
		return id;
	}
	
	public String getCategory(){
		return D_Category;
	}
	
	public List<Coping_Ability> getItems() {
		return items;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String setCategory(String D_Category){
		return this.D_Category = D_Category;
	}
	
	public void setItems(List<Coping_Ability> items){
		this.items = items;
	}
	
}

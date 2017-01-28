package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class DSM_AXIS_5 {
	
	private String id;
	
	private String category;
	
	private List<DSM_AXIS_5> items = new ArrayList<DSM_AXIS_5>();
	
	public String getId(){
		return id;
	}
	
	public String getCategory(){
		return category;
	}
	
	public List<DSM_AXIS_5> getItems() {
		return items;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setCategory(String DSM_Category){
		this.category = DSM_Category;
	}
	
	public void setItems(List<DSM_AXIS_5> items){
		this.items = items;
	}
	
}

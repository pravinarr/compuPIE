package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class DSM_AXIS_5 {
	
	private String id;
	
	private String DSM_Category;
	
	private List<DSM_AXIS_5> items = new ArrayList<DSM_AXIS_5>();
	
	public String getId(){
		return id;
	}
	
	public String getCategory(){
		return DSM_Category;
	}
	
	public List<DSM_AXIS_5> getItems() {
		return items;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String setCategory(String DSM_Category){
		return this.DSM_Category = DSM_Category;
	}
	
	public void setItems(List<DSM_AXIS_5> items){
		this.items = items;
	}
	
}

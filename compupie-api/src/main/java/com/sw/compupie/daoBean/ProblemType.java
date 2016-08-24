package com.sw.compupie.daoBean;
import java.util.ArrayList;
import java.util.List;

public class ProblemType {

	private int id;
	
	private String PT_Category;
	
	private List<ProblemType> items = new ArrayList<ProblemType>();
	
	public int getId(){
		return id;
	}
	
	public String getCategory(){
		return PT_Category;
	}
	
	public List<ProblemType> getItems() {
		return items;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String setCategory(String PT_Category){
		return this.PT_Category = PT_Category;
	}
	
	public void setItems(List<ProblemType> items){
		this.items = items;
	}
	
}

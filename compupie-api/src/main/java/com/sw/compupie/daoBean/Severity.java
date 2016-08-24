package com.sw.compupie.daoBean;

public class Severity {

	private int id;
	
	private String S_Category;
	
	
	public int getId(){
		return id;
	}
	
	public String getCategory(){
		return S_Category;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String setCategory(String S_Category){
		return this.S_Category = S_Category;
	}
	
	
}

package com.sw.compupie.model;

import java.util.List;

import com.sw.compupie.daoBean.Factor2Category;

public class Factor2Meta {
	
	private String categoryName;
	
	private List<Factor2Category> factor2Meta;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Factor2Category> getFactor2Meta() {
		return factor2Meta;
	}

	public void setFactor2Meta(List<Factor2Category> factor2Meta) {
		this.factor2Meta = factor2Meta;
	}
	

}

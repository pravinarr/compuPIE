package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class StrengthFactor1 {
		
		private int id;
		
		private String SF1_Category;
		
		private List<Strength_Factor3> items = new ArrayList<Strength_Factor3>();
		
		public int getId(){
			return id;
		}
		
		public String getCategory(){
			return SF1_Category;
		}
		
		public List<Strength_Factor3> getItems() {
			return items;
		}
		
		public void setId(int id){
			this.id = id;
		}
		
		public String setCategory(String D_Category){
			return this.SF1_Category = D_Category;
		}
		
		public void setItems(List<Strength_Factor3> items){
			this.items = items;
		}
		


}

package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Factor2Category;

public class Load_Factor2MetaInfo {
	Connection c = null;
	
	public Load_Factor2MetaInfo(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Factor2Category> getAllProblemsByCategory(String category) {
		//method getAllProblemsByCategory returns a List;
		List<Factor2Category> items = new ArrayList<Factor2Category>();
		Statement stmt = null;
		ResultSet rs;
		String tablename = getTableName(category);
		if(!tablename.equalsIgnoreCase("")){
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM "+tablename +" ;");
			while (rs.next()) {
				Factor2Category item = new Factor2Category();
				item.setId(rs.getInt("id"));
				item.setCategory(rs.getString("items"));
				items.add(item);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return items;
	}

	public static void main(String[] args){
		Load_SocialRoles test = new Load_SocialRoles();
		System.out.println(test.getAllProblemsByCategory().toString());
	}
	
	public String getTableName(String category){
		String tableName  = "";
		switch(category){
		case "Food/Nutrition":
			tableName = "FOOD_PROBLEMS";
			break;
		case "Shelter":
			tableName = "SHELTER_PROBLEM";
			break;
		case "Employment":
			tableName = "WORK_PROBLEM";
			break;
		case "Economic Resources":
			tableName = "ECONOMIC_PROBLEM";
			break;
		case "Transportation":
			tableName = "TRANSPORTATION_PROBLEM";
			break;
		case "Health Problems":
			tableName = "HEALTH_PROBLEM";
			break;
		case "Safety Problems":
			tableName = "SAFETY_PROBLEM";
			break;
		case "Social Service Problems":
			tableName = "SOCIAL_SERVICE_PROBLEM";
			break;
		case "Religious Group Problems":
			tableName = "VOLUNTARY_ASSOCIATION_PROBLEM ";
			break;
		case "Community Group Problems":
			tableName = "COMMUNITY_GROUP_PROBLEM";
			break;
		case "Education and Training":
			tableName = "EDUCATION_PROBLEM";
			break;
		case "Judicial and Legal":
			tableName = "JUDICIAL_PROBLEM";
			break;
		case "Affectional Support":
			tableName = "AFFECTIONAL_SUPPORT_PROBLEM";
			break;
		}
		return tableName;
	}
}

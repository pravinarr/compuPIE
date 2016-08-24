package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Strength_Factor2_Category;
import com.sw.compupie.daoBean.Strength_Factor2_Problems;

public class Load_Strength_Factor2{
	
	Connection c = null;
	
	public Load_Strength_Factor2(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Strength_Factor2_Category> getAllProblemsByCategory() {
		//method getAllProblemsByCategory returns a List;
		List<Strength_Factor2_Category> items = new ArrayList<Strength_Factor2_Category>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM STRENGTH_FACTOR2_CATEGORY;");
			while (rs.next()) {
				Strength_Factor2_Category item = new Strength_Factor2_Category();
				item.setId(rs.getInt("id"));
				item.setCategory(rs.getString("items"));
				item.setItems(getAllProblems(item.getId()));
				items.add(item);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return items;
	}

	public List<Strength_Factor2_Problems> getAllProblems(int categoryID) throws SQLException {
		Statement stmt = null;
		List<Strength_Factor2_Problems> items = new ArrayList<Strength_Factor2_Problems>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM STRENGTH_FACTOR2_PROBLEMS where sr_id = " + categoryID + ";");
		while (rs.next()) {
			Strength_Factor2_Problems item = new Strength_Factor2_Problems();
			item.setId(rs.getInt("id"));
			item.set_sr_Id(rs.getString("sr_id"));
			item.setItem(rs.getString("items"));
			items.add(item);
		}
		rs.close();
		stmt.close();
		return items;
	}
	
	public static void main(String[] args){
		Load_Strength_Factor2 test = new Load_Strength_Factor2();
		System.out.println(test.getAllProblemsByCategory().toString());
	}

}


package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.PhysicalHealthItem;
import com.sw.compupie.daoBean.PhysicalHealthProblems;

public class LoadPhysicalHealth {

	Connection c = null;

	public LoadPhysicalHealth() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<PhysicalHealthProblems> getAllProblemsByCategory() {

		List<PhysicalHealthProblems> items = new ArrayList<PhysicalHealthProblems>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM PHYSICAL_HEALTH_CATEGORIES;");
			while (rs.next()) {
				PhysicalHealthProblems item = new PhysicalHealthProblems();
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

	public List<PhysicalHealthItem> getAllProblems(int categoryID) throws SQLException {
		Statement stmt = null;
		List<PhysicalHealthItem> items = new ArrayList<PhysicalHealthItem>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PHYSICAL_HEALTH_PROBLEMS where PH_ID = " + categoryID + ";");
		while (rs.next()) {
			PhysicalHealthItem item = new PhysicalHealthItem();
			item.setId(rs.getInt("id"));
			item.setICD_CODE(rs.getString("ICD_CODE"));
			item.setItem(rs.getString("items"));
			items.add(item);
		}
		rs.close();
		stmt.close();
		return items;
	}
	
	public static void main(String[] args){
		LoadPhysicalHealth test = new LoadPhysicalHealth();
		System.out.println(test.getAllProblemsByCategory().toString());
	}

}

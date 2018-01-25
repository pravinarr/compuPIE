package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.SocialRoleCategory;
import com.sw.compupie.daoBean.SocialRoleProblems;

public class Load_SocialRoles{
	
	Connection c = null;
	
	public Load_SocialRoles(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<SocialRoleCategory> getAllProblemsByCategory() {
		//method getAllProblemsByCategory returns a List;
		List<SocialRoleCategory> items = new ArrayList<SocialRoleCategory>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SOCIAL_ROLE_CATEGORY;");
			while (rs.next()) {
				SocialRoleCategory item = new SocialRoleCategory();
				item.setId(rs.getInt("id"));
				item.setCategory(rs.getString("items"));
				item.setItems(getAllProblemsinbuilt(item.getId()));
				items.add(item);
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return items;
	}

	public List<SocialRoleProblems> getAllProblems(int categoryID) throws SQLException {
		Statement stmt = null;
		List<SocialRoleProblems> items = new ArrayList<SocialRoleProblems>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM social_role_problems where sr_id = " + categoryID + ";");
		while (rs.next()) {
			SocialRoleProblems item = new SocialRoleProblems();
			item.setId(rs.getInt("id"));
			item.set_sr_Id(rs.getString("sr_id"));
			item.setItem(rs.getString("items"));
			items.add(item);
		}
		rs.close();
		stmt.close();
		 
		return items;
	}
	
	public List<SocialRoleProblems> getAllProblemsinbuilt(int categoryID) throws SQLException {
		Statement stmt = null;
		List<SocialRoleProblems> items = new ArrayList<SocialRoleProblems>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM social_role_problems where sr_id = " + categoryID + ";");
		while (rs.next()) {
			SocialRoleProblems item = new SocialRoleProblems();
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
		Load_SocialRoles test = new Load_SocialRoles();
		System.out.println(test.getAllProblemsByCategory().toString());
	}

}


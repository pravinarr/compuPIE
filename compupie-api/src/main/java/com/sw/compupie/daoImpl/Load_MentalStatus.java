package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.MentalStatusProblemsBean;
import com.sw.compupie.daoBean.MentalStatus_Category;

public class Load_MentalStatus{
	
	Connection c = null;
	
	public Load_MentalStatus(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<MentalStatus_Category> getAllMentalStatusProblemsByCategory() {
		//method getAllProblemsByCategory returns a List;
		List<MentalStatus_Category> items = new ArrayList<MentalStatus_Category>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENTAL_STATUS_CATEGORY;");
			while (rs.next()) {
				MentalStatus_Category item = new MentalStatus_Category();
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

	public List<MentalStatusProblemsBean> getAllProblems(int categoryID) throws SQLException {
		Statement stmt = null;
		List<MentalStatusProblemsBean> items = new ArrayList<MentalStatusProblemsBean>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM MENTAL_STATUS_PROBLEMS where sr_id = " + categoryID + ";");
		while (rs.next()) {
			MentalStatusProblemsBean item = new MentalStatusProblemsBean();
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
		Load_MentalStatus test = new Load_MentalStatus();
		System.out.println(test.getAllMentalStatusProblemsByCategory().toString());
	}

}


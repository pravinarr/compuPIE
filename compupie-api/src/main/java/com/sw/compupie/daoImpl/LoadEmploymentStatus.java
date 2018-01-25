package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.EmploymentStatusBean;

public class LoadEmploymentStatus {

	Connection c = null;

	public LoadEmploymentStatus() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<EmploymentStatusBean> getAllEmploymentStatus() {
		List<EmploymentStatusBean> items = new ArrayList<EmploymentStatusBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYMENTSTATUS;");
			while (rs.next()) {
				EmploymentStatusBean item = new EmploymentStatusBean();
				item.setId(rs.getInt("id"));
				item.setItems(rs.getString("items"));
				items.add(item);
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static void main(String[] args) {
		Load_Duration test = new Load_Duration();
		System.out.println(test.getAllDuration().toString());
	}
}

package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.GenderBean;

public class LoadGender {
	Connection c = null;

	public LoadGender() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
		}

		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<GenderBean> getAllGenders() {
		// method getAllProblemType returns a List;
		List<GenderBean> items = new ArrayList<GenderBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM gender;");
			while (rs.next()) {
				GenderBean item = new GenderBean();
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

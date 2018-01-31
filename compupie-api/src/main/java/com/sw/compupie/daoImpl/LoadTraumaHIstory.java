package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.TraumaBean;

public class LoadTraumaHIstory {

	Connection c = null;

	public LoadTraumaHIstory() {
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

	public List<TraumaBean> getAllTraumaHistory() {
		// method getAllProblemType returns a List;
		List<TraumaBean> items = new ArrayList<TraumaBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM TRAUMA_HISTORY;");
			while (rs.next()) {
				TraumaBean item = new TraumaBean();
				item.setId(rs.getInt("id"));
				item.setCategory(rs.getString("category"));
				item.setItems(rs.getString("items"));
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
}

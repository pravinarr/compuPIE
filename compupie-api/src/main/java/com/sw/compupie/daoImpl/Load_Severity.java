package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Severity;

public class Load_Severity {
	
	Connection c = null;
	
	public Load_Severity(){	
		try	{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(false);
			}
		
		catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	public List<Severity> getAllSeverity() {
		//method getAllProblemType returns a List;
		List<Severity> items = new ArrayList<Severity>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SEVERITY;");
			while (rs.next()) {
				Severity item = new Severity();
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
	
		return items;
	}
	public static void main(String[] args){
		Load_Severity test = new Load_Severity();
		System.out.println(test.getAllSeverity().toString());
	}

	}

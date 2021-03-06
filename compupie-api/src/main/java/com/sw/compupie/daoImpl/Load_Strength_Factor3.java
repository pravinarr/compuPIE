package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Strength_Factor3;

public class Load_Strength_Factor3 {
	
	Connection c = null;
	
	public Load_Strength_Factor3(){	
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
	
	public List<Strength_Factor3> getAllStrength_Factor_3() {
		//method getAllProblemType returns a List;
		List<Strength_Factor3> items = new ArrayList<Strength_Factor3>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Strength_Factor3;");
			while (rs.next()) {
				Strength_Factor3 item = new Strength_Factor3();
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
		Load_Strength_Factor3 test = new Load_Strength_Factor3();
		System.out.println(test.getAllStrength_Factor_3().toString());
	}

	}

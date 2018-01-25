package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Coping_Ability;

public class Load_Coping_Ability {
	
	Connection c = null;
	
	public Load_Coping_Ability(){	
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
	
	public List<Coping_Ability> getAllCoping_Ability() {
		//method getAllProblemType returns a List;
		List<Coping_Ability> items = new ArrayList<Coping_Ability>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Coping_Ability;");
			while (rs.next()) {
				Coping_Ability item = new Coping_Ability();
				item.setId(rs.getInt("id"));
				item.setCategory(rs.getString("items"));
				items.add(item);
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return items;
	}
	public static void main(String[] args){
		Load_Coping_Ability test = new Load_Coping_Ability();
		System.out.println(test.getAllCoping_Ability().toString());
	}

	}

package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.SocialRoleProblemType;

public class Load_SocialRoleProblemType {
Connection c = null;
	
	public Load_SocialRoleProblemType(){	
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
	
	public List<SocialRoleProblemType> getAllProblemTypes() {
		//method getAllProblemType returns a List;
		List<SocialRoleProblemType> items = new ArrayList<SocialRoleProblemType>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SOCIAL_ROLE_PROBLEM_TYPE;");
			while (rs.next()) {
				SocialRoleProblemType item = new SocialRoleProblemType();
				item.setId(rs.getInt("id"));
				item.setProblemType(rs.getString("problemtype"));
				items.add(item);
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return items;
	}
	

}

package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.DSM_AXIS_5;

public class Load_DSM_AXIS_5 {
	
	Connection c = null;
	
	public Load_DSM_AXIS_5(){	
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
	
	public List<DSM_AXIS_5> getAllDSM_AXIS_5() {
		//method getAllProblemType returns a List;
		List<DSM_AXIS_5> items = new ArrayList<DSM_AXIS_5>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM DSM_AXIS_5;");
			while (rs.next()) {
				DSM_AXIS_5 item = new DSM_AXIS_5();
				item.setId(rs.getString("id"));
				//System.out.println(item.getId());
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
		Load_DSM_AXIS_5 test = new Load_DSM_AXIS_5();
		System.out.println(test.getAllDSM_AXIS_5().toString());
	}

	}


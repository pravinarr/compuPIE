package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Case_History;

public class Load_Case_History {
	
	Connection c = null;
	
	public Load_Case_History(){	
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
	
	public List<Case_History> getAllCase_History() {
		//method getAllProblemType returns a List;
		List<Case_History> items = new ArrayList<Case_History>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM CASE_HISTORY;");
			while (rs.next()) {
				Case_History item = new Case_History();
				item.setId(rs.getInt("id"));
				item.setClient_Id(rs.getInt("Client_Id"));
				item.setReason(rs.getString("ReasonForRefer"));
				item.setCurrentSituation(rs.getString("CurrentSituation"));
				item.setRelevantHistory(rs.getString("RelevantHistory"));
				item.setTraumaHistory(rs.getString("TraumaHistory"));

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
		Load_Case_History test = new Load_Case_History();
		System.out.println(test.getAllCase_History().toString());
	}

	}

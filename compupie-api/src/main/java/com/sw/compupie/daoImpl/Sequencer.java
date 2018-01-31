package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sequencer {
	Connection c = null;

	public void SequencerManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getmaxId() {
		SequencerManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM sequenc;");
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public int nextId(){
		SequencerManipulationConn();
		int out = 0;
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate("insert into sequenc (dummy) values (1);");
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out = getmaxId();
		return out;
		
	}
	
}

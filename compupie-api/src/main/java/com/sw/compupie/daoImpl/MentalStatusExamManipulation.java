package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sw.compupie.daoBean.MentalStatusExamBean;

public class MentalStatusExamManipulation {
	
	Connection c = null;

	public void mentalStatusManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public MentalStatusExamBean getStrength(int id) {
		mentalStatusManipulationConn();
		MentalStatusExamBean bean= null;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENTAL_EXAM where clientid =" + id + ";");
			while (rs.next()) {
				bean = new MentalStatusExamBean();
				bean.setId(rs.getInt("id"));
				bean.setClientId(rs.getInt("clientid"));
				bean.setProblemStr(rs.getString("PROBLEMSTR"));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public boolean ispresent(int id){
		mentalStatusManipulationConn();
		boolean present = false;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENTAL_EXAM where id = "+id+";");
			while (rs.next()) {
				present = true;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return present;
	}

	public int getmaxId(int clientID) {
		mentalStatusManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM MENTAL_EXAM where CLIENTID = "+clientID+";");
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

	public boolean saveNewStrength(MentalStatusExamBean info) {
		mentalStatusManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringTOSave(info));
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	public boolean updateNewStrength(MentalStatusExamBean info) {
		mentalStatusManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringToUpdate(info));
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	private String createStringTOSave(MentalStatusExamBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into MENTAL_EXAM (id,CLIENTID,PROBLEMSTR) values");
		buffer.append("(" + (getmaxId(info.getId()) + 1));
		buffer.append("," + info.getClientId());
		buffer.append(",\"" + info.getProblemStr() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(MentalStatusExamBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update MENTAL_EXAM set ");
		buffer.append("CLIENTID=" + info.getClientId());
		buffer.append(",PROBLEMSTR=\"" + info.getProblemStr() + "\"");
		buffer.append(" where id =" + info.getId()+" and CLIENTID="+info.getClientId()+";");
		return buffer.toString();
	}

}

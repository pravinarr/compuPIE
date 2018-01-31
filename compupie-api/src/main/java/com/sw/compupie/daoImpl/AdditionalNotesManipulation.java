package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sw.compupie.daoBean.AdditionalNotesBean;

public class AdditionalNotesManipulation {

	Connection c = null;

	public void additionalNotesManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public AdditionalNotesBean getStrength(int id) {
		additionalNotesManipulationConn();
		AdditionalNotesBean bean= null;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ADDITIONAL_NOTES where clientid =" + id + ";");
			while (rs.next()) {
				bean = new AdditionalNotesBean();
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
		additionalNotesManipulationConn();
		boolean present = false;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ADDITIONAL_NOTES where id = "+id+";");
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
		additionalNotesManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM ADDITIONAL_NOTES where CLIENTID = "+clientID+";");
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

	public boolean saveNewStrength(AdditionalNotesBean info) {
		additionalNotesManipulationConn();
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

	public boolean updateNewStrength(AdditionalNotesBean info) {
		additionalNotesManipulationConn();
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

	private String createStringTOSave(AdditionalNotesBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into ADDITIONAL_NOTES (id,CLIENTID,PROBLEMSTR) values");
		buffer.append("(" + (getmaxId(info.getId()) + 1));
		buffer.append("," + info.getClientId());
		buffer.append(",\"" + info.getProblemStr() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(AdditionalNotesBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update ADDITIONAL_NOTES set ");
		buffer.append("CLIENTID=" + info.getClientId());
		buffer.append(",PROBLEMSTR=\"" + info.getProblemStr() + "\"");
		buffer.append(" where id =" + info.getId()+" and CLIENTID="+info.getClientId()+";");
		return buffer.toString();
	}

	
	
}

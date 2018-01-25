package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sw.compupie.daoBean.Factor4MoreInfoBean;

public class Factor4MoreInfoTableManipulation {
	Connection c = null;

	public void Factor4ManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Factor4MoreInfoBean getStrength(int id) {
		Factor4ManipulationConn();
		Factor4MoreInfoBean bean= null;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR4_MOREINFO where clientid =" + id + ";");
			while (rs.next()) {
				bean = new Factor4MoreInfoBean();
				bean.setId(rs.getInt("id"));
				bean.setClientId(rs.getInt("clientid"));
				bean.setProblemStr(rs.getString("PROBLEMSTR"));
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public boolean ispresent(int id){
		Factor4ManipulationConn();
		boolean present = false;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR4_MOREINFO where id = "+id+";");
			while (rs.next()) {
				present = true;
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return present;
	}

	public int getmaxId(int clientID) {
		Factor4ManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM FACTOR4_MOREINFO where CLIENTID = "+clientID+";");
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean saveNewStrength(Factor4MoreInfoBean info) {
		Factor4ManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringTOSave(info));
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	public boolean updateNewStrength(Factor4MoreInfoBean info) {
		Factor4ManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringToUpdate(info));
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	private String createStringTOSave(Factor4MoreInfoBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into FACTOR4_MOREINFO (id,CLIENTID,PROBLEMSTR) values");
		buffer.append("(" + (getmaxId(info.getId()) + 1));
		buffer.append("," + info.getClientId());
		buffer.append(",\"" + info.getProblemStr() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(Factor4MoreInfoBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update FACTOR4_MOREINFO set ");
		buffer.append("CLIENTID=" + info.getClientId());
		buffer.append(",PROBLEMSTR=\"" + info.getProblemStr() + "\"");
		buffer.append(" where id =" + info.getId()+" and CLIENTID="+info.getClientId()+";");
		return buffer.toString();
	}


	
}

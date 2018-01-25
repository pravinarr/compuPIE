package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.StrengthAndResourcesBean;

public class StrengthResourceManipulation {
	Connection c = null;

	public void StrengthResourceManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public StrengthAndResourcesBean getStrength(int id) {
		StrengthResourceManipulationConn();
		StrengthAndResourcesBean bean= null;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM STRENGTH_AND_RESOURCES where clientid =" + id + ";");
			while (rs.next()) {
				bean = new StrengthAndResourcesBean();
				bean.setId(rs.getInt("id"));
				bean.setClientid(rs.getInt("clientid"));
				bean.setFactor1(rs.getString("factor1_str"));
				bean.setFactor2(rs.getString("factor2_str"));
				bean.setFactor3(rs.getString("factor3_str"));
				bean.setFactor4(rs.getString("factor4_str"));
			}
			rs.close();
			stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public int getmaxId(int clientID) {
		StrengthResourceManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM STRENGTH_AND_RESOURCES where CLIENTID = "+clientID+";");
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

	public boolean saveNewStrength(StrengthAndResourcesBean info) {
		StrengthResourceManipulationConn();
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

	public boolean updateNewStrength(StrengthAndResourcesBean info) {
		StrengthResourceManipulationConn();
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

	private String createStringTOSave(StrengthAndResourcesBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into STRENGTH_AND_RESOURCES (id,CLIENTID,factor1_str,factor2_str,factor3_str,factor4_str) values");
		buffer.append("(" + (getmaxId(info.getId()) + 1));
		buffer.append("," + info.getClientid());
		buffer.append(",\"" + info.getFactor1() + "\"");
		buffer.append(",\"" + info.getFactor2()+ "\"");
		buffer.append(",\"" + info.getFactor3() + "\"");
		buffer.append(",\"" + info.getFactor4() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(StrengthAndResourcesBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update STRENGTH_AND_RESOURCES set ");
		buffer.append("CLIENTID=" + info.getClientid());
		buffer.append(",factor1_str=\"" + info.getFactor1() + "\"");
		buffer.append(",factor2_str=\"" + info.getFactor2()+ "\"");
		buffer.append(",factor3_str=\"" + info.getFactor3() + "\"");
		buffer.append(",factor4_str=\"" + info.getFactor4() + "\"");
		buffer.append(" where id =" + info.getId()+" and CLIENTID="+info.getClientid()+";");
		return buffer.toString();
	}
}

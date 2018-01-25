package com.sw.compupie.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sw.compupie.daoBean.MentalStatusExamBean;
import com.sw.compupie.daoBean.MentalStatusObject;

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

	public MentalStatusExamBean getStrength(int id)  {
		mentalStatusManipulationConn();
		MentalStatusExamBean bean = null;
		Statement stmt = null;
		ResultSet rs;
		ObjectMapper mapper = new ObjectMapper();
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENTAL_EXAM where clientid =" + id + ";");
			while (rs.next()) {
				bean = new MentalStatusExamBean();
				bean.setId(rs.getInt("id"));
				bean.setClientId(rs.getInt("clientid"));
				bean.setProblemStr(
						mapper.readValue(rs.getString("PROBLEMSTR"), new TypeReference<List<MentalStatusObject>>() {
						}));
			}
			rs.close();
			stmt.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public boolean ispresent(int id) {
		mentalStatusManipulationConn();
		boolean present = false;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENTAL_EXAM where id = " + id + ";");
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
		mentalStatusManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM MENTAL_EXAM where CLIENTID = " + clientID + ";");
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

	public boolean saveNewStrength(MentalStatusExamBean info) {
		mentalStatusManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringTOSave(info));
			stmt.close();
			 
		} catch (Exception e) {
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
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	private String createStringTOSave(MentalStatusExamBean info) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into MENTAL_EXAM (id,CLIENTID,PROBLEMSTR) values");
		buffer.append("(" + (getmaxId(info.getId()) + 1));
		buffer.append("," + info.getClientId());
		buffer.append(",'" + mapper.writeValueAsString(info.getProblemStr()) + "');");
		return buffer.toString();
	}

	private String createStringToUpdate(MentalStatusExamBean info) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringBuffer buffer = new StringBuffer();
		buffer.append("update MENTAL_EXAM set ");
		buffer.append("CLIENTID=" + info.getClientId());
		buffer.append(",PROBLEMSTR='" + mapper.writeValueAsString(info.getProblemStr()) + "'");
		buffer.append(" where id =" + info.getId() + " and CLIENTID=" + info.getClientId() + ";");
		return buffer.toString();
	}

}

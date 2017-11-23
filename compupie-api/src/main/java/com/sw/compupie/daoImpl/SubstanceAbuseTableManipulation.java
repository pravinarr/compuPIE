package com.sw.compupie.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jackson.map.ObjectMapper;

import com.sw.compupie.daoBean.SubstanceAbuseBean;

public class SubstanceAbuseTableManipulation {

	public Connection c = null;

	public void SubstanceAbuseTableManipulation() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SubstanceAbuseTableManipulation s = new SubstanceAbuseTableManipulation();
		
		SubstanceAbuseBean bean = new SubstanceAbuseBean();
		bean.setOtherProblems("Youare");
		s.saveNewAbuse(bean);
		//s.getSubstanceHistory(0);

	}

	public SubstanceAbuseBean getSubstanceHistory(int id) {
		SubstanceAbuseTableManipulation();
		SubstanceAbuseBean list = new SubstanceAbuseBean();
		ObjectMapper mapper = new ObjectMapper();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SUBSTANCEABUSE where clientId =" + id + ";");
			while (rs.next()) {
				
				list = mapper.readValue(rs.getString("REPORT"), SubstanceAbuseBean.class);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getmaxId(int clientID) {
		SubstanceAbuseTableManipulation();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM SUBSTANCEABUSE where CLIENTID = " + clientID + ";");
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

	public boolean saveNewAbuse(SubstanceAbuseBean info) {
		SubstanceAbuseTableManipulation();
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

	public boolean updateNewHIstory(SubstanceAbuseBean info) {
		SubstanceAbuseTableManipulation();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringToUpdate(info));
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	private String createStringTOSave(SubstanceAbuseBean info) {
		int id = getmaxId(info.getClientId());
		info.setId(++id);
		ObjectMapper mapper = new ObjectMapper();
		String sqlString = null;
		try {
			sqlString = createInsertString(mapper.writeValueAsString(info), id, info.getClientId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sqlString;
	}

	private String createInsertString(String value, int id, int clientid) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into SUBSTANCEABUSE (id,clientid,REPORT) values");
		buffer.append("(" + id);
		buffer.append("," + clientid);
		buffer.append(",'" + value + "');");
		return buffer.toString();
	}

	private String createStringToUpdate(SubstanceAbuseBean info) throws Exception{
		StringBuffer buffer = new StringBuffer();
		ObjectMapper mapper = new ObjectMapper();
		buffer.append("update SUBSTANCEABUSE set ");
		buffer.append("clientId=" + info.getClientId());
		buffer.append(",report='" + mapper.writeValueAsString(info) + "'");
		buffer.append(" where id =" + info.getId() + " and clientId=" + info.getClientId() + ";");
		return buffer.toString();
	}
}

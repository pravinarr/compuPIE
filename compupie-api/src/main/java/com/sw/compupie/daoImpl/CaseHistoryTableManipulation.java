package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.CaseHistoryBean;

public class CaseHistoryTableManipulation {
	Connection c = null;

	public void CaseHistoryTableManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CaseHistoryBean> getCaseHistory(int id) {
		CaseHistoryTableManipulationConn();
		List<CaseHistoryBean> list = new ArrayList<CaseHistoryBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM CASE_HISTORY where clientId =" + id + ";");
			while (rs.next()) {
				CaseHistoryBean info = new CaseHistoryBean();
				info.setId(rs.getInt("id"));
				info.setCurrentSituation(rs.getString("currentSituation"));
				info.setClientId(rs.getInt("clientId"));
				info.setReasonForRefer(rs.getString("reasonForRefer"));
				info.setRelevantHistory(rs.getString("relevantHistory"));
				info.setTraumaHistory(rs.getString("traumaHistory"));
				info.setNotes(rs.getString("notes"));
				list.add(info);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getmaxId(int clientID) {
		CaseHistoryTableManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM CASE_HISTORY where clientId = "+clientID+";");
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

	public boolean saveNewHistory(CaseHistoryBean info) {
		CaseHistoryTableManipulationConn();
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

	public boolean updateNewHIstory(CaseHistoryBean info) {
		CaseHistoryTableManipulationConn();
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

	private String createStringTOSave(CaseHistoryBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into CASE_HISTORY (id,clientId,reasonForRefer,currentSituation,relevantHistory,notes,traumaHistory) values");
		buffer.append("(" + (getmaxId(info.getClientId()) + 1));
		buffer.append("," + info.getClientId());
		buffer.append(",\"" + info.getReasonForRefer() + "\"");
		buffer.append(",\"" + info.getCurrentSituation()+ "\"");
		buffer.append(",\"" + info.getRelevantHistory() + "\"");
		buffer.append(",'" + info.getNotes() + "'");
		buffer.append(",\"" + info.getTraumaHistory() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(CaseHistoryBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update CASE_HISTORY set ");
		buffer.append("clientId=" + info.getClientId());
		buffer.append(",reasonForRefer=\"" + info.getReasonForRefer() + "\"");
		buffer.append(",currentSituation=\"" + info.getCurrentSituation()+ "\"");
		buffer.append(",relevantHistory=\"" + info.getRelevantHistory() + "\"");
		buffer.append(",notes='" + info.getNotes() + "'");
		buffer.append(",traumaHistory=\"" + info.getTraumaHistory() + "\"");
		buffer.append(" where id =" + info.getId()+" and clientId="+info.getClientId()+";");
		return buffer.toString();
	}

}

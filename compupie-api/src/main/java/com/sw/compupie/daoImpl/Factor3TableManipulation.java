package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Factor3Bean;

public class Factor3TableManipulation {
	Connection c = null;

	public void Factor3TableManipulationcreate() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Factor3Bean> getFactorInfo(int id) {
		Factor3TableManipulationcreate();
		List<Factor3Bean> list = new ArrayList<Factor3Bean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR3 where clientId =" + id + ";");
			while (rs.next()) {
				Factor3Bean info = new Factor3Bean();
				info.setId(rs.getInt("id"));
				info.setdsmDiagnosis(rs.getString("problem"));
				info.setdiagnosisSource(rs.getString("source"));
				info.setServerity(rs.getString("serverity"));
				info.setDuration(rs.getString("duration"));
				info.setCopingAbitity(rs.getString("copingAbitity"));
				info.setPriority(rs.getString("priority"));
				info.setGoal(rs.getString("goal"));
				info.setRecommendedInter(rs.getString("recommendedInter"));
				info.setExpectedOutcome(rs.getString("expectedOutcome"));
				info.setClientId(rs.getInt("clientId"));
				info.setFollowup(rs.getInt("followUp"));
				info.setSpecifier(rs.getString("specifier"));
				
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
	
	public List<Factor3Bean> getFactorInfoForFollowup(int clientID,int followup) {
		Factor3TableManipulationcreate();
		List<Factor3Bean> list = new ArrayList<Factor3Bean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR3 where clientId =" + clientID + " and followUp = "+ followup +";");
			while (rs.next()) {
				Factor3Bean info = new Factor3Bean();
				info.setId(rs.getInt("id"));
				info.setdsmDiagnosis(rs.getString("problem"));
				info.setdiagnosisSource(rs.getString("source"));
				info.setServerity(rs.getString("serverity"));
				info.setDuration(rs.getString("duration"));
				info.setCopingAbitity(rs.getString("copingAbitity"));
				info.setPriority(rs.getString("priority"));
				info.setGoal(rs.getString("goal"));
				info.setRecommendedInter(rs.getString("recommendedInter"));
				info.setExpectedOutcome(rs.getString("expectedOutcome"));
				info.setClientId(rs.getInt("clientId"));
				info.setFollowup(rs.getInt("followUp"));
				info.setMHP(rs.getString("MHP"));
				info.setNoDx(rs.getString("PossibleDx"));
				info.setDenies(rs.getString("DeniesMH"));
				info.setSpecifier(rs.getString("specifier"));
				
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
	
	public List<Factor3Bean> getFactorInfo(int id,int clientId) {
		List<Factor3Bean> list = new ArrayList<Factor3Bean>();
		Factor3TableManipulationcreate();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR3 where clientId =" + clientId + " and id ="+id+";");
			while (rs.next()) {
				Factor3Bean info = new Factor3Bean();
				info.setId(rs.getInt("id"));
				info.setdsmDiagnosis(rs.getString("problem"));
				info.setdiagnosisSource(rs.getString("source"));
				info.setServerity(rs.getString("serverity"));
				info.setDuration(rs.getString("duration"));
				info.setCopingAbitity(rs.getString("copingAbitity"));
				info.setPriority(rs.getString("priority"));
				info.setGoal(rs.getString("goal"));
				info.setRecommendedInter(rs.getString("recommendedInter"));
				info.setExpectedOutcome(rs.getString("expectedOutcome"));
				info.setClientId(rs.getInt("clientId"));
				info.setFollowup(rs.getInt("followUp"));
				info.setMHP(rs.getString("MHP"));
				info.setNoDx(rs.getString("PossibleDx"));
				info.setDenies(rs.getString("DeniesMH"));
				info.setSpecifier(rs.getString("specifier"));
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
	
	public List<Factor3Bean> getFactorInfoByFollowUp(int followUp,int clientId) {
		List<Factor3Bean> list = new ArrayList<Factor3Bean>();
		Factor3TableManipulationcreate();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR3 where clientId =" + clientId + " and followUp ="+followUp+";");
			while (rs.next()) {
				Factor3Bean info = new Factor3Bean();
				info.setId(rs.getInt("id"));
				info.setdsmDiagnosis(rs.getString("problem"));
				info.setdiagnosisSource(rs.getString("source"));
				info.setServerity(rs.getString("serverity"));
				info.setDuration(rs.getString("duration"));
				info.setCopingAbitity(rs.getString("copingAbitity"));
				info.setPriority(rs.getString("priority"));
				info.setGoal(rs.getString("goal"));
				info.setRecommendedInter(rs.getString("recommendedInter"));
				info.setExpectedOutcome(rs.getString("expectedOutcome"));
				info.setClientId(rs.getInt("clientId"));
				info.setFollowup(rs.getInt("followUp"));
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
		Factor3TableManipulationcreate();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM FACTOR3 where clientId="+clientID+";");
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

	public boolean saveNewFactory(Factor3Bean info) {
		Factor3TableManipulationcreate();
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

	public boolean updateNewFactory(Factor3Bean info) {
		Factor3TableManipulationcreate();
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

	private String createStringTOSave(Factor3Bean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into FACTOR3 (id,problem,source,serverity,duration,copingAbitity,"
				+ "priority,goal,recommendedInter,expectedOutcome,specifier,clientId,followUp) values");
		buffer.append("(" + (getmaxId(info.getClientId()) + 1));
		buffer.append(",\"" + info.getdsmDiagnosis() + "\"");
		buffer.append(",\"" + info.getdiagnosisSource() + "\"");
		buffer.append(",\"" + info.getServerity() + "\"");
		buffer.append(",\"" + info.getDuration() + "\"");
		buffer.append(",\"" + info.getCopingAbitity() + "\"");
		buffer.append(",\"" + info.getPriority() + "\"");
		buffer.append(",\"" + info.getGoal() + "\"");
		buffer.append(",\"" + info.getRecommendedInter() + "\"");
		buffer.append(",\"" + info.getExpectedOutcome() + "\"");
		buffer.append(",\"" + info.getSpecifier() + "\"");
		buffer.append("," + info.getClientId());
		buffer.append("," + info.getFollowup() +");");
	/*	buffer.append(",\"" + info.getMHP() + "\"");
		buffer.append(",\"" + info.getNoDx() + "\"");
		buffer.append(",\"" + info.getDenies() + "\" );");*/
		return buffer.toString();
	}

	private String createStringToUpdate(Factor3Bean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update FACTOR3 set ");
		buffer.append("problem=\"" + info.getdsmDiagnosis() + "\"");
		buffer.append(",source=\"" + info.getdiagnosisSource() + "\"");
		buffer.append(",serverity=\"" + info.getServerity() + "\"");
		buffer.append(",duration=\"" + info.getDuration() + "\"");
		buffer.append(",copingAbitity=\"" + info.getCopingAbitity() + "\"");
		buffer.append(",priority=\"" + info.getPriority() + "\"");
/*		buffer.append(",MHP=\"" + info.getMHP() + "\"");
		buffer.append(",PossibleDx=\"" + info.getNoDx() + "\"");
		buffer.append(",DeniesMH=\"" + info.getDenies() + "\"");*/
		buffer.append(",recommendedInter=\"" + info.getRecommendedInter() + "\"");
		buffer.append(",expectedOutcome=\"" + info.getExpectedOutcome() + "\"");
		//buffer.append(",clientId=" + info.getClientId());
		buffer.append(",followUp=" + info.getFollowup());
		buffer.append(" , specifier=\"" + info.getExpectedOutcome() + "\"");
		buffer.append(" where id =" + info.getId());
		buffer.append(" and clientId=" + info.getClientId()+";");
		return buffer.toString();
	}
}

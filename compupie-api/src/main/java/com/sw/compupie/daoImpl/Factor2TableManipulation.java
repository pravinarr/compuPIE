package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Factor2Bean;

public class Factor2TableManipulation {

	Connection c = null;

	public void Factor2TableManipulationcreate() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteFactory(String clientId, String problemId) {
		Factor2TableManipulationcreate();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringDelete(clientId, problemId));
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update > 0);
	}

	private String createStringDelete(String clientId, String problemId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from FACTOR2 where id = " + problemId + " and clientId = " + clientId + ";");
		return buffer.toString();
	}

	public List<Factor2Bean> getFactorInfoLast(int id) {
		Factor2TableManipulationcreate();
		List<Factor2Bean> list = new ArrayList<Factor2Bean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR2 where clientId =" + id + ""
					+ " and followup = (select max(id) from follow_up where clientid = "+id +"  and stage in (0,2));");
			while (rs.next()) {
				Factor2Bean info = new Factor2Bean();
				info.setId(0);
				info.setproblemCategory(rs.getString("problemCategory"));
				info.setProblemType(rs.getString("problemType"));
				info.setServerity(rs.getString("serverity"));
				info.setDuration(rs.getString("duration"));
				info.setCopingAbitity(rs.getString("copingAbitity"));
				info.setPriority(rs.getString("priority"));
				info.setGoal(rs.getString("goal"));
				info.setRecommendedInter(rs.getString("recommendedInter"));
				info.setExpectedOutcome(rs.getString("expectedOutcome"));
				info.setClientId(rs.getInt("clientId"));
				info.setFollowup(0);
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
	
	
	public List<Factor2Bean> getFactorInfo(int id) {
		Factor2TableManipulationcreate();
		List<Factor2Bean> list = new ArrayList<Factor2Bean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR2 where clientId =" + id + ";");
			while (rs.next()) {
				Factor2Bean info = new Factor2Bean();
				info.setId(rs.getInt("id"));
				info.setproblemCategory(rs.getString("problemCategory"));
				info.setProblemType(rs.getString("problemType"));
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

	public List<Factor2Bean> getFactorInfo(int id,String problem) {
		Factor2TableManipulationcreate();
		List<Factor2Bean> list = new ArrayList<Factor2Bean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR2 where clientId =" + id + " and problemCategory=\""+problem+"\";");
			while (rs.next()) {
				Factor2Bean info = new Factor2Bean();
				info.setId(rs.getInt("id"));
				info.setproblemCategory(rs.getString("problemCategory"));
				info.setProblemType(rs.getString("problemType"));
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
	public List<Factor2Bean> getFactorInfo(int id, int clientId) {
		List<Factor2Bean> list = new ArrayList<Factor2Bean>();
		Factor2TableManipulationcreate();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR2 where clientId =" + clientId + " and id =" + id + ";");
			while (rs.next()) {
				Factor2Bean info = new Factor2Bean();
				info.setId(rs.getInt("id"));
				info.setproblemCategory(rs.getString("problemCategory"));
				info.setProblemType(rs.getString("problemType"));
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

	public List<Factor2Bean> getFactorInfoByFollowUp(int followup, int clientId) {
		List<Factor2Bean> list = new ArrayList<Factor2Bean>();
		Factor2TableManipulationcreate();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FACTOR2 where clientId =" + clientId + " and followUp =" + followup + ";");
			while (rs.next()) {
				Factor2Bean info = new Factor2Bean();
				info.setId(rs.getInt("id"));
				info.setproblemCategory(rs.getString("problemCategory"));
				info.setProblemType(rs.getString("problemType"));
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
		Factor2TableManipulationcreate();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM FACTOR2 where clientId=" + clientID + ";");
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

	public boolean saveNewFactory(Factor2Bean info) {
		Factor2TableManipulationcreate();
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

	public boolean updateNewFactory(Factor2Bean info) {
		Factor2TableManipulationcreate();
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

	private String createStringTOSave(Factor2Bean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into FACTOR2 (id,problemCategory,problemType,serverity,duration,copingAbitity,"
				+ "priority,goal,recommendedInter,expectedOutcome,clientId,followUp) values");
		buffer.append("(" + (getmaxId(info.getClientId()) + 1));
		buffer.append(",\"" + info.getProblemCategory() + "\"");
		buffer.append(",\"" + info.getProblemType() + "\"");
		buffer.append(",\"" + info.getServerity() + "\"");
		buffer.append(",\"" + info.getDuration() + "\"");
		buffer.append(",\"" + info.getCopingAbitity() + "\"");
		buffer.append(",\"" + info.getPriority() + "\"");
		buffer.append(",\"" + info.getGoal() + "\"");
		buffer.append(",\"" + info.getRecommendedInter() + "\"");
		buffer.append(",\"" + info.getExpectedOutcome() + "\"");
		buffer.append("," + info.getClientId());
		buffer.append("," + info.getFollowup() + ");");
		return buffer.toString();
	}

	private String createStringToUpdate(Factor2Bean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update FACTOR2 set ");
		buffer.append("problemCategory=\"" + info.getProblemCategory() + "\"");
		buffer.append(",problemType=\"" + info.getProblemType() + "\"");
		buffer.append(",serverity=\"" + info.getServerity() + "\"");
		buffer.append(",duration=\"" + info.getDuration() + "\"");
		buffer.append(",copingAbitity=\"" + info.getCopingAbitity() + "\"");
		buffer.append(",priority=\"" + info.getPriority() + "\"");
		buffer.append(",goal=\"" + info.getGoal() + "\"");
		buffer.append(",recommendedInter=\"" + info.getRecommendedInter() + "\"");
		buffer.append(",expectedOutcome=\"" + info.getExpectedOutcome() + "\"");
		//buffer.append(",clientId=" + info.getClientId());
		buffer.append(",followUp=" + info.getFollowup());
		buffer.append(" where id =" + info.getId());
		buffer.append(" and clientId=" + info.getClientId()+";");
		return buffer.toString();
	}

	

}

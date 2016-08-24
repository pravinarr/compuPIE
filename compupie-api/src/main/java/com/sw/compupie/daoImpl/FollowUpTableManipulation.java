package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Factor1Bean;
import com.sw.compupie.daoBean.FollowUpBean;

public class FollowUpTableManipulation {
	Connection c = null;

	public void FollowUpTableManipulation() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<FollowUpBean> getFollowUpInfo(int id) {
		FollowUpTableManipulation();
		List<FollowUpBean> list = new ArrayList<FollowUpBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FOLLOW_UP where clientId =" + id + ";");
			while (rs.next()) {
				FollowUpBean info = new FollowUpBean();
				info.setId(rs.getInt("id"));
				info.setClientid(rs.getInt("clientid"));
				info.setDate(rs.getString("dof"));
				info.setAccessedBy(rs.getString("accessedBy"));
				info.setStage(rs.getInt("stage"));
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
	
	public List<FollowUpBean> getFollowUpInfo(int id,int clientId) {
		FollowUpTableManipulation();
		List<FollowUpBean> list = new ArrayList<FollowUpBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FOLLOW_UP where clientId =" + clientId + " and id ="+id+";");
			while (rs.next()) {
				FollowUpBean info = new FollowUpBean();
				info.setId(rs.getInt("id"));
				info.setClientid(rs.getInt("clientid"));
				info.setDate(rs.getString("dof"));
				info.setStage(rs.getInt("stage"));
				info.setAccessedBy(rs.getString("accessedBy"));
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
		FollowUpTableManipulation();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM Follow_up where clientId="+clientID+" ;");
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

	public boolean saveNewFollowUp(FollowUpBean info) {
		FollowUpTableManipulation();
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

		private String createStringTOSave(FollowUpBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into FOLLOW_UP (id,clientid,dof,stage,accessedBy) values");
		buffer.append("(" + (getmaxId(info.getClientid())+1));
		buffer.append("," + info.getClientid() + "");
		buffer.append(",date('now')");
		buffer.append("," + info.getStage()+" ");
		buffer.append(",\"" + info.getAccessedBy() + "\");");
		return buffer.toString();
	}

}

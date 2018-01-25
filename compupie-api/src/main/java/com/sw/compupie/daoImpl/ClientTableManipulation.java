package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.ClientBean;
import com.sw.compupie.daoBean.SearchBean;

public class ClientTableManipulation {

	Connection c = null;

	public void ClientTableManipulationConn() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<com.sw.compupie.daoBean.SearchBean> searchClient(SearchBean bean) {
		ClientTableManipulationConn();
		List<SearchBean> list = new ArrayList<SearchBean>();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(createSearchString(bean));
			while (rs.next()) {
				FollowUpTableManipulation mn = new FollowUpTableManipulation();
				SearchBean info = new SearchBean();
				info.setId(rs.getInt("id"));
				info.setCity(rs.getString("city"));
				info.setLastName(rs.getString("lastname"));
				info.setMiddleName(rs.getString("middlename"));
				info.setFirstName(rs.getString("firstname"));
				info.setClientId(rs.getString("clientId"));
				info.setGender(rs.getString("gender"));
				info.setStreet(rs.getString("street"));
				info.setState(rs.getString("stateName"));
				info.setZipCode(rs.getString("zipcode"));
				info.setPhone(rs.getString("phone"));
				info.setIsClosed(""+mn.isClosed(info.getId()));
				list.add(info);
			}
			rs.close();
			stmt.close();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	

	public boolean deleteFromSearch(String id) {
		ClientTableManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate("update CLIENT_INFO set deleted='Y' where id=\""+id+"\"");
			stmt.close();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (update==1)?true:false;
	}

	public ClientBean getClientInfo(String id) {
		ClientTableManipulationConn();
		ClientBean info = new ClientBean();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM CLIENT_INFO where id =" + id + ";");
			while (rs.next()) {
				info.setId(rs.getInt("id"));
				info.setCity(rs.getString("city"));
				info.setLastname(rs.getString("lastname"));
				info.setMiddleName(rs.getString("middlename"));
				info.setFirstname(rs.getString("firstname"));
				info.setClientId(rs.getString("clientId"));
				info.setGender(rs.getString("gender"));
				info.setMaritalStatus(rs.getString("maritalStatus"));
				info.setOccupatiion(rs.getString("occupatiion"));
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
				info.setDob(rs.getString("dob"));
				info.setStreet(rs.getString("street"));
				info.setCity(rs.getString("city"));
				info.setStateName(rs.getString("stateName"));
				info.setZipcode(rs.getString("zipcode"));
				info.setPhone(rs.getString("phone"));
				info.setEthnicity(rs.getString("ethnicity"));
				info.setReferredBy(rs.getString("referredBy"));
				info.setAdditional(rs.getString("additional"));
				info.setNoOfChildrenInCare(rs.getInt("noOfChildrenInCare"));
				info.setEmploymentStatus(rs.getString("employmentStatus"));
				info.setHighestLevelOfEducation(rs.getString("highestLevelOfEducation"));
				info.setLivingArrangement(rs.getString("livingArrangement"));
				info.setAssessedBy(rs.getString("assessedBy"));
			}
			rs.close();
			stmt.close();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return info;
	}

	public int getmaxId() {
		ClientTableManipulationConn();
		int id = 0;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT max(id) FROM CLIENT_INFO;");
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

	public int saveNewClient(ClientBean info) {
		
		int cliId = getmaxId()+1;
		ClientTableManipulationConn();
		Statement stmt = null;
		int update = 0;
		try {
			stmt = c.createStatement();
			update = stmt.executeUpdate(createStringTOSave(info));
			stmt.close();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(update==1){
			cliId = 0;
		}
		return cliId;
	}

	public boolean updateNewClient(ClientBean info) {
		ClientTableManipulationConn();
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

	private String createStringTOSave(ClientBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"insert into CLIENT_INFO (id,lastname,middlename,firstname,clientId,gender,maritalStatus,dob,occupatiion,street,city,stateName,zipcode,phone,ethnicity,referredBy,additional,"
						+ "noOfChildrenInCare,highestLevelOfEducation,employmentStatus,livingArrangement,assessedBy) values");
		buffer.append("(" + info.getId());
		buffer.append(",\"" + info.getLastname() + "\"");
		buffer.append(",\"" + info.getMiddleName() + "\"");
		buffer.append(",\"" + info.getFirstname() + "\"");
		buffer.append(",\"" + info.getClientId() + "\"");
		buffer.append(",\"" + info.getGender() + "\"");
		buffer.append(",\"" + info.getMaritalStatus() + "\"");
		buffer.append(",\"" + info.getDob() + "\"");
		buffer.append(",\"" + info.getOccupatiion() + "\"");
		buffer.append(",\"" + info.getStreet() + "\"");
		buffer.append(",\"" + info.getCity() + "\"");
		buffer.append(",\"" + info.getStateName() + "\"");
		buffer.append(",\"" + info.getZipcode() + "\"");
		buffer.append(",\"" + info.getPhone() + "\"");
		buffer.append(",\"" + info.getEthnicity() + "\"");
		buffer.append(",\"" + info.getReferredBy() + "\"");
		buffer.append(",\"" + info.getAdditional() + "\" ");
		buffer.append("," + info.getNoOfChildrenInCare());
		buffer.append(",\"" + info.getHighestLevelOfEducation() + "\"");
		buffer.append(",\"" + info.getEmploymentStatus() + "\"");
		buffer.append(",\"" + info.getLivingArrangement() + "\"");
		buffer.append(",\"" + info.getAssessedBy() + "\");");
		return buffer.toString();
	}

	private String createStringToUpdate(ClientBean info) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update CLIENT_INFO set ");
		buffer.append("id=" + info.getId());
		buffer.append(",lastname=\"" + info.getLastname() + "\"");
		buffer.append(",middlename=\"" + info.getMiddleName() + "\"");
		buffer.append(",firstname=\"" + info.getFirstname() + "\"");
		buffer.append(",clientId=\"" + info.getClientId() + "\"");
		buffer.append(",gender=\"" + info.getGender() + "\"");
		buffer.append(",maritalStatus=\"" + info.getMaritalStatus() + "\"");
		buffer.append(",dob=\"" + info.getDob() + "\"");
		buffer.append(",occupatiion=\"" + info.getOccupatiion() + "\"");
		buffer.append(",street=\"" + info.getStreet() + "\"");
		buffer.append(",city=\"" + info.getCity() + "\"");
		buffer.append(",stateName=\"" + info.getStateName() + "\"");
		buffer.append(",zipcode=\"" + info.getZipcode() + "\"");
		buffer.append(",phone=\"" + info.getPhone() + "\"");
		buffer.append(",ethnicity=\"" + info.getEthnicity() + "\"");
		buffer.append(",referredBy=\"" + info.getReferredBy() + "\"");
		buffer.append(",additional=\"" + info.getAdditional() + "\" ");
		buffer.append(",noOfChildrenInCare=" + info.getNoOfChildrenInCare());
		buffer.append(",highestLevelOfEducation=\"" + info.getHighestLevelOfEducation() + "\"");
		buffer.append(",employmentStatus=\"" + info.getEmploymentStatus() + "\"");
		buffer.append(",livingArrangement=\"" + info.getLivingArrangement() + "\"");
		buffer.append(",assessedBy=\"" + info.getAssessedBy() + "\"");
		buffer.append(" where id =" + info.getId() + ";");
		return buffer.toString();
	}

	private String createSearchString(SearchBean bean) {
		StringBuffer buffer = new StringBuffer();
		int i = 1;
		buffer.append("SELECT * FROM CLIENT_INFO where deleted != 'Y' ");
		if (bean.getFirstName() != null && !bean.getFirstName().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" firstname like '%" + bean.getFirstName() + "%'");
		}
		if (bean.getLastName() != null && !bean.getLastName().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" lastname like '%" + bean.getLastName() + "%'");
		}
		if (bean.getMiddleName() != null && !bean.getMiddleName().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" middlename like '%" + bean.getMiddleName() + "%'");
		}
		if (bean.getPhone() != null && !bean.getPhone().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" phone like '%" + bean.getPhone() + "%'");
		}
		if (bean.getZipCode() != null && !bean.getZipCode().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" zipcode like '%" + bean.getZipCode() + "%'");
		}
		if (bean.getClientId() != null && !bean.getClientId().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" clientId like '%" + bean.getClientId() + "%'");
		}
		if (bean.getUserId() != null && !bean.getUserId().trim().equalsIgnoreCase("")) {
			if (i == 0) {
				buffer.append(" where ");
				i++;
			}else{
				buffer.append(" and ");
			}
			buffer.append(" assessedBy like '%" + bean.getUserId() + "%'");
		}
		buffer.append(";");
		return buffer.toString();
	}

}

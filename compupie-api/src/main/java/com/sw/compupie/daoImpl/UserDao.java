package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sw.compupie.security.SecureCompuPie;


public class UserDao {

	Connection c = null;

	public void UserDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
			c.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean ispresent(String username,String password){
		UserDao();
		boolean present = false;
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM USER_SECURITY where username=\""+username +"\" and password= \""+password +"\";");
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
	
	public boolean update(String cusername,String cpassword,String nusername,String npassword){
		UserDao();
		boolean present = true;
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			  stmt.executeUpdate("update USER_SECURITY set username=\""+nusername +"\" , password= \""+npassword +"\""
					+ "where username=\""+cusername +"\";");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return present;
	}
	public static void main(String[] args){
		UserDao user = new UserDao();
		SecureCompuPie secure = new SecureCompuPie();
		try {
			user.ispresent("asd", secure.decrypt("asd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

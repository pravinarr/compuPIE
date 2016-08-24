package com.sw.compupie.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.StrengthFactor1;

public class Load_strength_facor1 {

		Connection c = null;
		
		public Load_strength_facor1(){	
			try	{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
				c.setAutoCommit(false);
				}
			
			catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
		
		public List<StrengthFactor1> getAllStrength_Factor_1() {
			//method getAllProblemType returns a List;
			List<StrengthFactor1> items = new ArrayList<StrengthFactor1>();
			Statement stmt = null;
			ResultSet rs;
			try {
				stmt = c.createStatement();
				rs = stmt.executeQuery("SELECT * FROM STRENGTH_FACTOR1;");
				while (rs.next()) {
					StrengthFactor1 item = new StrengthFactor1();
					item.setId(rs.getInt("id"));
					item.setCategory(rs.getString("items"));
					items.add(item);
				}
				rs.close();
				stmt.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			return items;
		}
		public static void main(String[] args){
			Load_strength_facor1 test = new Load_strength_facor1();
		}



}

package com.sw.compupie.daoImpl;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class MentalStatus_Test {
		
		public static void main(String[] args){
			
			Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite::resource:compuPIEMetaInfo.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully\n");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM MENTAL_STATUS_PROBLEMS;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         int sr_id = rs.getInt("sr_id");
		         String  address = rs.getString("items");
		         System.out.println( "ID = " + id );
		         System.out.println( "SR_ID = " + sr_id );
		         System.out.println( "items = " + address );
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");
		}
	}

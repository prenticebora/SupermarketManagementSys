/**
 * 
 */
package org.learning.j2ee.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author brliu
 *
 */
public class SupermarketMySql {
	private static Connection connection = null;
	private PreparedStatement preparedStatement;
	private static String mysqlUser = "brliu";
	private static String mysqlPassword = "123456";
	private static String className = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/db_supermarket";
	
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error in loading MySQL JDBC driver...");
				
				throw new RuntimeException(e);
			}
			
			try {
				connection = DriverManager.getConnection(url , mysqlUser, mysqlPassword);
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("Error in connecting to MySQL DB " + url);
				
				throw new RuntimeException(e);
			}
		}
		
		return connection;
	}
	
}

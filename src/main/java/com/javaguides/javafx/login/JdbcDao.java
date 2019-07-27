package com.javaguides.javafx.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDao {
	
	// Replace below database url, username and password with your actual database credentials
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_registration?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";
	private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
	
	public boolean validate(String emailId, String password) throws SQLException {
		
        // Step 1: Establishing a Connection and 
		// try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }           	
            
            
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

	public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

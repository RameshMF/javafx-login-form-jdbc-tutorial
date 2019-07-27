package com.javaguides.javafx.login;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class LoginController {
    
    @FXML
    private TextField emailIdField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button submitButton;
    
    @FXML
    public void login(ActionEvent event) throws SQLException{
    
    	Window owner = submitButton.getScene().getWindow();
    	
    	System.out.println(emailIdField.getText());
    	System.out.println(passwordField.getText());
    	
    	if(emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your email id");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter a password");
            return;
        }
        
    	String emailId = emailIdField.getText();
    	String password = passwordField.getText();
    	
    	JdbcDao jdbcDao = new JdbcDao();
    	boolean flag = jdbcDao.validate(emailId, password);

    	if(!flag) {
    		infoBox("Please enter correct Email and Password", null, "Failed");
    	}else {
    		infoBox("Login Successful!", null, "Failed");
    	}
    }
    
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

package com.javaguides.javafx.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	System.out.println(getClass());
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/login_form.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}

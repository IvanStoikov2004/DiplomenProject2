package com.example.libraryinterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePage {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button registrate;
    @FXML
    private URL location;

    @FXML
    private Button LOgin;


    //after click on this button we open window where we can login in the site with our email and password from database
    @FXML
    void LoginButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window=(Stage) LOgin.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
    //after click on this button we open window where we can registrate to the site
    @FXML
    void registrationButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Stage window=(Stage) registrate.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void initialize() {
        assert LOgin != null : "fx:id=\"LOgin\" was not injected: check your FXML file 'HomePage.fxml'.";

    }

}

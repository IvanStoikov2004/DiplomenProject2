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

public class SelectOption {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CatalogFXID;

    @FXML
    private Button ElectronicBooksFXID;



    @FXML
    void CATALOGButton(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window=(Stage) CatalogFXID.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void ElectronicBooksButton(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("ElectronicBooks.fxml"));
        Stage window=(Stage) CatalogFXID.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void initialize() {
        assert CatalogFXID != null : "fx:id=\"CatalogFXID\" was not injected: check your FXML file 'SelectOption.fxml'.";
        assert ElectronicBooksFXID != null : "fx:id=\"ElectronicBooksFXID\" was not injected: check your FXML file 'SelectOption.fxml'.";

    }

}

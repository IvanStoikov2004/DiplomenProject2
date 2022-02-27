package com.example.libraryinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
      //  stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
//run the programe
    public static void main(String[] args) {
        launch();


    }
}
//s tazi komanda ne mi se powtarq snimkata : -fx-background-repeat: stretch;
//hello-view.fxml
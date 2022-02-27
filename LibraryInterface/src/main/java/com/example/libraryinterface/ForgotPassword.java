package com.example.libraryinterface;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ForgotPassword {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Label ErrorLabel;
    @FXML
    private URL location;

    @FXML
    private TextField emailFXID;

    @FXML
    private TextField firstNameFXID;


    @FXML
    private PasswordField newPasswordFXID;

    @FXML
    private TextField phoneFXID;

    @FXML
    private Button returnFXID;

    @FXML
    private TextField secondNameFXID;

    @FXML
    void clearButton(ActionEvent event) {
firstNameFXID.clear();
secondNameFXID.clear();
phoneFXID.clear();
emailFXID.clear();
newPasswordFXID.clear();
        newPasswordFXID.setPromptText("new password");
        ErrorLabel.setText("");
    }
//syzdavame metod za edit na baza danni registraciq
    public void EditRegisterDataBase() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
        PreparedStatement ps = null;
        try {
            String sql = "Update registartion set password=? Where number=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,newPasswordFXID.getText());
            ps.setString(2,phoneFXID.getText());
            ps.execute();
            System.out.println("DAta has be Update");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void editButton(ActionEvent event) throws SQLException {
       newPasswordFXID.setText(NewPasswordShow);
        //Първо сверяваме дали всички необходими данни от таблицата са налични и ако са вече прилагаме метод за модифициране на таблицата за забравена парола
        if (firstNameFXID.getText().equals("")||secondNameFXID.getText().equals("")||phoneFXID.getText().equals("")||emailFXID.getText().equals("")||newPasswordFXID.getText().equals("")){
            ErrorLabel.setText("There are empty files");
        }else {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "Select * from registartion Where firstName=? and secondName=? and number=? and email=? ";
                ps = con.prepareStatement(sql);
                // ps.setInt(1, id_client);
                ps.setString(1, firstNameFXID.getText());
                ps.setString(2, secondNameFXID.getText());
                ps.setString(3, phoneFXID.getText());
                ps.setString(4, emailFXID.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("tuk sum");
                    EditRegisterDataBase();
                } else {
                    //дава грешка
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Wrong data");
                    alert.show();
                    // System.out.println("Greshka");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                //дава грешка
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Wrong data");
                alert.show();
            }
        }
    }


    public  int counterNewPasswordShow=0;
    //promenliva koqto sydyrva parolata
    public  String  NewPasswordShow="";
    @FXML
    void NewPasswordShow(ActionEvent event) {
        counterNewPasswordShow++;
        if (counterNewPasswordShow % 2 == 1) {
            NewPasswordShow = newPasswordFXID.getText();
            newPasswordFXID.clear();
            newPasswordFXID.setPromptText(NewPasswordShow);
        } else{
            newPasswordFXID.setText(NewPasswordShow);
        }
    }

    @FXML
    void returnButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window=(Stage) returnFXID.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void initialize() {
        assert emailFXID != null : "fx:id=\"emailFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
        assert firstNameFXID != null : "fx:id=\"firstNameFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
        assert newPasswordFXID != null : "fx:id=\"newPasswordFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
        assert phoneFXID != null : "fx:id=\"phoneFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
        assert returnFXID != null : "fx:id=\"returnFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
        assert secondNameFXID != null : "fx:id=\"secondNameFXID\" was not injected: check your FXML file 'ForgotPassword.fxml'.";

    }

}
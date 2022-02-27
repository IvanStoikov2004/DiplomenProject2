package com.example.libraryinterface;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Registration {

    @FXML
    private ResourceBundle resources;
    @FXML
    private DatePicker Birthday;
    @FXML
    private TextField numberTextField;
    @FXML
    private URL location;

    @FXML
    private TextField EmailLAbel;

    @FXML
    private TextField FirstNameLabel;

    @FXML
    private PasswordField PAsswordLabel;

    @FXML
    private Button RETURNtoHomepageFromRegistartionForm;

    @FXML
    private TextField SecondNameLabel;
    @FXML
    private Label ErrorLabel;

    //Metod za vkarvane v bazata danni

//this is same method as method insertUser from Hello controller fail but with different table name and with other name of columns
    private static void Registration( String firstname, String secondName,String email,String password,String number,LocalDate BirthdayDATE) throws SQLException {


        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO registartion ( firstName,secondName,email,password,number,BirthdayDATE) Values(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            // ps.setInt(1, id_client);
            ps.setString(1, firstname);
            ps.setString(2, secondName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, number);
            //като взимаме дата от DatePicker в javaFX  трябва да зададем функцията Date.valueOf()
            ps.setDate(6, Date.valueOf(BirthdayDATE));
            ps.execute();
            System.out.println("Data has be inserted");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    @FXML
    void RETURNtoHomepageFromRegistartionForm(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage window=(Stage)  RETURNtoHomepageFromRegistartionForm.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    void clearAllFromRegistrationForm(ActionEvent event) {
EmailLAbel.clear();
FirstNameLabel.clear();
SecondNameLabel.clear();
PAsswordLabel.clear();
numberTextField.clear();
Birthday.getEditor().clear();
ErrorLabel.setText("");
        PAsswordLabel.setPromptText("password:");
    }


    //syzdavame broiach za pokazvane na parolata
    public  int counter=0;
    //promenliva koqto sydyrva parolata
    public  String  showpassword="";

    @FXML
    void ShowPassword(ActionEvent event) {

//ako counter e nechetno cislo to se pokazva parolata inache se skriwa
            counter++;
            if (counter % 2 == 1) {
                showpassword =PAsswordLabel.getText();
                PAsswordLabel.clear();
                PAsswordLabel.setPromptText(showpassword);
            } else{
                PAsswordLabel.setText(showpassword);
            }

        }


    @FXML
    void registrationButton(ActionEvent event) throws SQLException {
        //zadavame text na poleto s parola ako e na prom text kakto e v login formata
        if (FirstNameLabel.getText().equals("")||SecondNameLabel.getText().equals("")||EmailLAbel.getText().equals("")||PAsswordLabel.getText().equals("")||numberTextField.getText().equals("")||Birthday.getValue()==null){
            ErrorLabel.setText("There are empty files");
        }else if (EmailLAbel.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")&&numberTextField.getText().length()==10){

            //tuk pravim syshtoto(ako coounter e nechetno chislo toest parolata se vizda se vzima kato promtext inache se vzima kato text ot poleto
            if (counter%2==1){
                PAsswordLabel.setText(showpassword);
            }else{

            }

            String Firstname=FirstNameLabel.getText();
            String SecondName= SecondNameLabel.getText();
            String email=EmailLAbel.getText();
            String password=PAsswordLabel.getText();
            String numberr= numberTextField.getText();
//взимаме датата от date pickera
            LocalDate date=Birthday.getValue();

            Registration(Firstname,SecondName,email,password,numberr,date);
        }else{
            ErrorLabel.setText("Email or number is not valid");
        }

    }

    @FXML
    void initialize() {
        assert EmailLAbel != null : "fx:id=\"EmailLAbel\" was not injected: check your FXML file 'registration.fxml'.";
        assert FirstNameLabel != null : "fx:id=\"FirstNameLabel\" was not injected: check your FXML file 'registration.fxml'.";
        assert PAsswordLabel != null : "fx:id=\"PAsswordLabel\" was not injected: check your FXML file 'registration.fxml'.";
        assert RETURNtoHomepageFromRegistartionForm != null : "fx:id=\"RETURNtoHomepageFromRegistartionForm\" was not injected: check your FXML file 'registration.fxml'.";
        assert SecondNameLabel != null : "fx:id=\"SecondNameLabel\" was not injected: check your FXML file 'registration.fxml'.";

    }

}


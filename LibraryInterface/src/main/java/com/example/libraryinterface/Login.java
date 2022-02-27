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

public class Login extends  Registration{

    @FXML
    private Button showPasswordFXID;
    @FXML
    private Button login1;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField PasswordLabel;

    @FXML
    private TextField emailLabel;
    @FXML
    private Label ErrorLabel;
    @FXML
    private Button HomePage;

    @FXML
    private Hyperlink ForgotPasswordFXID;

    @FXML
    void ReturnToHomePage(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage window=(Stage) HomePage.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }



//pravim tazi constantna promenliva za da zapazq emaila na choveka koito se e lognal v prilozenieto i da mu izkarva v table view neshtata samo na tozi chovek
    public static String email="";


    @FXML
    void Login(ActionEvent event) throws IOException, SQLException {
        //kazvame che ako counter e nechetno chislo to parolata se e pokazala i vzimame parolata ot promtexta inache vzimame parolata ot texta v samiq label
       if (counter%2==1){
           PasswordLabel.setText(password);
       }else{

       }
if (emailLabel.getText().equals(null)||PasswordLabel.getText().equals(null)){
ErrorLabel.setText("There are empty files");
}else{
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
    PreparedStatement ps = null;
    ResultSet rs=null;
    try {
        String sql = "Select * from registartion Where email=? and password=? ";
        ps = con.prepareStatement(sql);
        // ps.setInt(1, id_client);
        ps.setString(1, emailLabel.getText());
        ps.setString(2, PasswordLabel.getText());
        rs= ps.executeQuery();
        if (rs.next()){
            System.out.println("Login is sucesfull");
            //kogato imeila syvpadne go zapametqvam w promenlivata
            email=emailLabel.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SelectOption.fxml"));
            Parent root=FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
            Stage window=(Stage) login1.getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }else{
            //дава грешка
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error message");
            alert.show();
            // System.out.println("Greshka");
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        //дава грешка
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error message");
        alert.show();
    }
}

    }
////////GOTOVI ZAQVKI S BAZATA DANNI
//method delete
/*
public void Delete() throws SQLException {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
    PreparedStatement ps = null;
    try {
        String sql = "Delete from addbook Where name=?";
        ps = con.prepareStatement(sql);
        ps.setString(1,"Imeto na reda koito iskame da istriem ili//  pishim label,.getText()");
        ps.execute();
        System.out.println("DAta has be dalete");
    } catch (SQLException e) {
        System.out.println(e.toString());
    }

}
*/
    /*
public void EditADDBOKK() throws SQLException {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
    PreparedStatement ps = null;
    try {
        String sql = "Update addbook set name=? Where author=?";
        ps = con.prepareStatement(sql);
        ps.setString(1,"ime ili label.getText()");
        ps.setString(2,"ime ili label.getText()");
        ps.execute();
        System.out.println("DAta has be Update");
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    }
*/
    /*
public void SelectAll() throws SQLException {
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
    PreparedStatement ps=null;
    ResultSet rs=null;
    try {
        String sql="SELECT * FROM addbook";
        ps=con.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()) {
            String name=rs.getString("name");
            String author=rs.getString("author");
            String serialNumber=rs.getString("serialNumber");
            System.out.println(name+" "+author+" "+serialNumber);
        }
    }catch(SQLException e) {
        System.out.println(e.toString());
    }
}

*/
@FXML
void ForgotPassword(ActionEvent event) throws IOException {
    Parent root=FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
    Stage window=(Stage) ForgotPasswordFXID.getScene().getWindow();
    window.setScene(new Scene(root));
    window.show();
}


    @FXML
    void ClearAll(ActionEvent event) throws SQLException {
        //VIKANE NA GOTOVITE ZAQVKI
        //Delete();
        //EditADDBOKK();
       // SelectAll();
emailLabel.clear();
PasswordLabel.clear();
        PasswordLabel.setPromptText("password");
    }


    //syzdavame metod za pokazvane na parola chrez promText obache sym go napravil taka che kogato se natisne login button da se zadava tozi red (  PasswordLabel.setText(password);) za da moze da vzemem texta ot poleto a ne prom texta i taka shte e vqrno vlizaneto
    //inache ako vzemem texta dokato ima prom text to teksta e null i shte dade greshka
    //zatowa vuv login button slagam tozi red (PasswordLabel.setText(password);) za da se zadadde tekst na password poleto
    //syzdavame broiach za pokazvane na parolata
    public  int counter=0;
//promenliva koqto sydyrva parolata
public  String  password="";
    @FXML
    void showPasswordButton(ActionEvent event) {
//ako counter e nechetno cislo to se pokazva parolata inache se skriwa
            counter++;
            if (counter % 2 == 1) {
                password = PasswordLabel.getText();
                PasswordLabel.clear();
                PasswordLabel.setPromptText(password);
            } else{
                PasswordLabel.setText(password);
            }

    }

    @FXML
    void initialize() {
        assert ForgotPasswordFXID != null : "fx:id=\"ForgotPasswordFXID\" was not injected: check your FXML file 'login.fxml'.";
        assert HomePage != null : "fx:id=\"HomePage\" was not injected: check your FXML file 'login.fxml'.";
        assert PasswordLabel != null : "fx:id=\"PasswordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert login1 != null : "fx:id=\"login1\" was not injected: check your FXML file 'login.fxml'.";


    }


}

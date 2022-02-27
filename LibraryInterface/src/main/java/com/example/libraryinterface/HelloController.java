package com.example.libraryinterface;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.events.MouseEvent;





//Ima dva klasa za TableView// towa e ediniq(vtoriq e ModelTable)



//nasledqvam klas LOGIn za da izpolzvam const promenliva email i da zapisvam emaila na horata
public class HelloController extends Login implements Initializable  {
//Create method who insert  user data into database
    private static void insertBOOk( String bookname, String author,String serialNumber,Object DATA,String zanr,String emailPeople) throws SQLException {
        //create connection to database as we write on the change on word(library) our database name, on the change root we write our user name in the mysql software and in the end we write our password on the mysql account
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
        //create variable and we set a value null where we save our mysql script for database
        PreparedStatement ps = null;
        //create try catch block who will catch and return  exeption from block of code write in him
        try {
            //create variable where we set our sql script with who we work with database from intelij software
            String sql = "INSERT INTO addbook ( name,author,serialnumber,Data,Zanr,email) Values(?,?,?,?,?,?)";
            //set value to our variable which we created a while aqo(преди малко)
            ps = con.prepareStatement(sql);
          //set user data in the variable ps(as value)
            ps.setString(1, bookname);
            ps.setString(2, author);
            ps.setString(3, serialNumber);
            ps.setObject(4, DATA);
            ps.setString(5, zanr);
            ps.setString(6,emailPeople);
            //we execute this block of code
            ps.execute();
            //If this code is correctly program will return in the console "Data has be inserted"
            System.out.println("Data has be inserted");
            //otherwaise programe will return exception
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

//this are a button who created automatic from the Scene builder
    @FXML
    private Button logOut;

    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField zanr;
    @FXML
    private URL location;

    @FXML
    private TextField labelBookAuthor;

    @FXML
    private TextField labelBookName;

    @FXML
    private TextField labelBookSerialNumber;

    @FXML
    private Button refreshTableView;

    @FXML
    private Button EditTableView;

    @FXML
    private Button deleteTableView;
//After click on button   book's name,author and serial number will be inserted in database book


    @FXML
    void AddBook(ActionEvent event) throws SQLException {
        //we get value from different label
String bookNAME=labelBookName.getText();
String author=labelBookAuthor.getText();
String serialNumber=labelBookSerialNumber.getText();
String zanrNaKniga=zanr.getText();
//Взимаме датата като обект и я вписваме в таблицата
LocalDate Date = LocalDate.now();

//insert values from label in method who insert them to database
insertBOOk(bookNAME,author,serialNumber,Date,zanrNaKniga,email);
refreshTableViewMethod();

    }
//After click on button who clear all label
    @FXML
    void ClearAll(ActionEvent event) {
        labelBookName.clear();
        labelBookAuthor.clear();
        labelBookSerialNumber.clear();
        zanr.clear();
    }

    @FXML
    void initialize() {
        assert TableBook != null : "fx:id=\"TableBook\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_Author != null : "fx:id=\"col_Author\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_Date != null : "fx:id=\"col_Date\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_Name != null : "fx:id=\"col_Name\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_SerialNumber != null : "fx:id=\"col_SerialNumber\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_Zanr != null : "fx:id=\"col_Zanr\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert col_email != null : "fx:id=\"col_email\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert labelBookAuthor != null : "fx:id=\"labelBookAuthor\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert labelBookName != null : "fx:id=\"labelBookName\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert labelBookSerialNumber != null : "fx:id=\"labelBookSerialNumber\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert refreshTableView != null : "fx:id=\"refreshTableView\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert zanr != null : "fx:id=\"zanr\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert EditTableView != null : "fx:id=\"EditTableView\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert deleteTableView != null : "fx:id=\"deleteTableView\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

//after click on this button we close one window and we will go to the other window
    @FXML
    void LogOutButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window=(Stage) logOut.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }



////////////////////Ottuk e tablicata/////////////////////////////////
    @FXML
    private TableView<ModelTable> TableBook;

    @FXML
    private TableColumn<ModelTable, String> col_Author;

    @FXML
    private TableColumn<ModelTable, String> col_Name;

    @FXML
    private TableColumn<ModelTable, String> col_SerialNumber;

    @FXML
    private TableColumn<ModelTable, String> col_Zanr;

    @FXML
    private TableColumn<ModelTable, String> col_email;

    @FXML
    private TableColumn<ModelTable, Date> col_Date;
//syzdavameList sydyrzasht informaciqta ot tablicata i q vkwarvame w TableView
    ObservableList<ModelTable> list= FXCollections.observableArrayList();



//sled startirane vsichko koeto e v Database se vkarva v TableView
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //pravim connection to Database i vkarvame vsichko w lista
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

           // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from addbook Where email=? ";
            ps = con.prepareStatement(sql);
            // ps.setInt(1, id_client);
            ps.setString(1,email );
            rs= ps.executeQuery();
            while(rs.next()){
                //vkarvame vsichko v lista
                list.add(new ModelTable(rs.getString("name"),rs.getString("author"),rs.getString("serialNumber"),rs.getString("Zanr"),rs.getString("email"),rs.getString("Data")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_Name.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        col_Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        col_SerialNumber.setCellValueFactory(new PropertyValueFactory<>("SerialNumber"));
        col_Zanr.setCellValueFactory(new PropertyValueFactory<>("Zanr"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
//popylvame tablicata
        TableBook.setItems(list);
    }


//sled natiskane na tozi buton izchistwame lista sydyrzsht informaciqta za tableView i vkarvame vsichko nanovo za da se vkarat i novite neshta
    @FXML
    void refreshTableViewOnAction(ActionEvent event) {
        //prosto izvikvam metod refreshTableView
        refreshTableViewMethod();

        //moze da go zapisha i taka kato otdolu pod tozi red no taka se otnasq zamo za sled natiskane na butona
        /*
        PreparedStatement ps = null;
        ResultSet rs=null;
        try{
            list.clear();
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from addbook Where email=? ";
            ps = con.prepareStatement(sql);
            // ps.setInt(1, id_client);
            ps.setString(1,email );
            rs= ps.executeQuery();

            while(rs.next()){
                list.add(new ModelTable(rs.getString("name"),rs.getString("author"),rs.getString("serialNumber"),rs.getString("Zanr"),rs.getString("email"),rs.getString("Data")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        */

    }
//syzdavame metod refresh(a ne prosto refreshOnAction-toest metod koito se izpylnqva sled natiskane na buton) za da moze da byde dostypen do vsicki drugi metodi
    public void refreshTableViewMethod(){
        PreparedStatement ps = null;
        ResultSet rs=null;
        try{
            list.clear();
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from addbook Where email=? ";
            ps = con.prepareStatement(sql);
            // ps.setInt(1, id_client);
            ps.setString(1,email );
            rs= ps.executeQuery();

            while(rs.next()){
                list.add(new ModelTable(rs.getString("name"),rs.getString("author"),rs.getString("serialNumber"),rs.getString("Zanr"),rs.getString("email"),rs.getString("Data")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Създаваме index за select метода за таблицата
    int index=-1;
    //select metod for tableView
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index=TableBook.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        labelBookName.setText(col_Name.getCellData(index).toString());
        labelBookAuthor.setText(col_Author.getCellData(index).toString());
        labelBookSerialNumber.setText(col_SerialNumber.getCellData(index).toString());
        zanr.setText(col_Zanr.getCellData(index).toString());
    }

    //edit metod for tableView

    @FXML
    void EditTableViewOnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs=null;
try{
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
    String valueNameEdit=labelBookName.getText();
    String valueAuthorEdit=labelBookAuthor.getText();
    String valueSerialNumberEdit=labelBookSerialNumber.getText();
    String valueZanrEdit= zanr.getText();
    //Чрез тази техника  [ '"(тук се пише променливата)"' ] може да зададем стойност и да променим нещо в базата данни с променлива без допълнителен ред и да пишем че е равна на ? и после да задаваме стойност(чрез тази техника ние директно задаваме стойност в самия sql код)
    String sql="update addbook set name= '"+ valueNameEdit+"',author= '"+valueAuthorEdit+"',serialNumber='"+valueSerialNumberEdit+"',Zanr='"+valueZanrEdit+"' where serialNumber='"+valueSerialNumberEdit+"'";
    ps=con.prepareStatement(sql);
    ps.execute();
    System.out.println("Table is edit");
    refreshTableViewMethod();
}catch (Exception e){
    e.printStackTrace();
}

    }

    //delete metod
    @FXML
    void deleteTableViewOnAction(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs=null;

        try{
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");
            String sql="delete from addbook where name=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,labelBookName.getText());
            ps.execute();
            System.out.println("Table is delete");
            refreshTableViewMethod();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

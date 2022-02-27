package com.example.libraryinterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.Desktop;


public class ElectronicBooks implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ReadElectronicBooksFXID;

    @FXML
    private TableView<ModleTableElectronicBooks> TableElectronicBooks;

    @FXML
    private TableColumn<ModleTableElectronicBooks, String> col_AuthorElectronicBook;

    @FXML
    private TableColumn<ModleTableElectronicBooks, String> col_NameElectronicBook;

    @FXML
    private TableColumn<ModleTableElectronicBooks, String> col_SerialNumberElectronicBook;

    @FXML
    private TableColumn<ModleTableElectronicBooks, String> col_ZanrElectronicBook;

    @FXML
    private Button returnButtonFXID;

    @FXML
    private TextField SearchBar;

//filtri
@FXML
    private MenuItem authorBratqGrimFiltyrFXID;

    @FXML
    private MenuItem authorAstridLingrendFiltyrFXID;

    @FXML
    private MenuItem authorMadamDioBomonFiltyrFXID;

    @FXML
    private MenuItem zanrPrikazkaFiltyrFXID;

    @FXML
    private MenuItem zanrRomanFiltyrFXID;

    @FXML
    private MenuItem authorSharlPeroFiltyrFXID;

    @FXML
    private MenuItem authorAnRaisFXID;

    @FXML
    private MenuItem authorAniVivantiFiltyrFXID;

    @FXML
    private MenuItem authorBiliLecFXID;

    @FXML
    private MenuItem authorByrtrisSmolFXID;

    @FXML
    private MenuItem authorDaniylSilwaFiltyrFXID;

    @FXML
    private MenuItem authorDeboraHarkensFXID;

    @FXML
    private MenuItem authorDiinKuncFiltyrFXID;

    @FXML
    private MenuItem authorDjonDWindvFiltyrFXID;

    @FXML
    private MenuItem authorDjuliAnUokyrFXID;

    @FXML
    private MenuItem authorEndruLourynsFiltyrFXID;

    @FXML
    private MenuItem authorKoliinFolkynFXID;

    @FXML
    private MenuItem authorLeiGriinuudFXID;

    @FXML
    private MenuItem authorStiwynHyntyrFXID;

    @FXML
    private MenuItem authorStiwynKingFiltyrFXID;

    @FXML
    private MenuItem authorWalentinPopovFiltyrFXID;

    @FXML
    private MenuItem authorWiktoriqAivqrdFXID;

    @FXML
    private MenuItem authorZulVernFiltyrFXID;


    @FXML
    private MenuItem authorMaikylKraitynFXID;

    @FXML
    private MenuItem authorMaikylRidpadFXID;

    @FXML
    private MenuItem authorMaikylSkotFXID;

    @FXML
    private MenuItem authorNiilGeitynFXID;

    @FXML
    private MenuItem authorPityrStrobFiltyrFXID;


    @FXML
    private MenuItem WithoutFiltersFXID;


    @FXML
    private MenuItem zanrFentyziFiltyrFXID;

    @FXML
    private MenuItem zanrHoryrFiltyrFXID;

    @FXML
    private MenuItem zanrTriloyrFiltyrFXID;



    @FXML
    void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
        Stage window=(Stage) returnButtonFXID.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
//list za table view(извежда всичко от базата данни в tableview
    ObservableList<ModleTableElectronicBooks> listTableViewElectronicBooks= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       StartMethod();

    }

    //startirasht metod (този метод изжезда жсицхко от базата данни и дава възможност за търсене от базата данни)-Той е главния метод който извикваме при стартиране на програмата а именно метод(initialize(URL url, ResourceBundle resourceBundle)) който е три реда по-нагоре
    public void StartMethod(){
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //pravim connection to Database i vkarvame vsichko w lista
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from electronicbooks ";
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                //vkarvame vsichko v lista
                listTableViewElectronicBooks.add(new ModleTableElectronicBooks(rs.getString("NameBook"),rs.getString("Author"),rs.getString("SerialNumber"),rs.getString("ZANR")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_NameElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookName"));
        col_AuthorElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookAuthor"));
        col_SerialNumberElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookSerialNumber"));
        col_ZanrElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookZanr"));
//popylvame tablicata
        TableElectronicBooks.setItems(listTableViewElectronicBooks);

        //izvikvane na search bar-a
        try {
            searchElectronicBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



//syzdavame metod i promenlivi chrez koito shte zapisvame informaciqta ot selectiraniq red na TableView-to (sled koeto chrez imeto na knigata shte razberem koi pdf da otvorim)
    int index=-1;
    String ElectronicBooksNameValue=null;
    String ElectronicBooksAuthorValue=null;
    String ElectronicBooksSerialNumberValue=null;
    String ElectronicBooksZanrValue=null;
    //select metod for tableView
    public void getSelectedElectronicBook(javafx.scene.input.MouseEvent mouseEvent) {
        index=TableElectronicBooks.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
       ElectronicBooksNameValue=col_NameElectronicBook.getCellData(index).toString();
       ElectronicBooksAuthorValue=col_AuthorElectronicBook.getCellData(index).toString();
       ElectronicBooksSerialNumberValue=col_AuthorElectronicBook.getCellData(index).toString();
        ElectronicBooksZanrValue=col_ZanrElectronicBook.getCellData(index).toString();
    }



// Create SearchBar //from here
ObservableList<ModleTableElectronicBooks> SerachBarList= FXCollections.observableArrayList();

    public  void searchElectronicBooks() throws SQLException {
        //izpolzvani elementi:
        //neshtata v scripta sa ot Database promenlivite
        //neshtat v while cikyla sa ot promenlivite ot database
        // ModleTableElectronicBooks e konstrukta v syshtiq klas
        //col_NameElectronicBook (tezi promenlivi sa kolonite na tableView-to a neshtata v tqh sa ot  get i set metodite v clas ModleTableElectronicBooks)
        //TableElectronicBooks - tova e fxid ot scene buildera na samata Tablica(tableView-to)
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //pravim connection to Database i vkarvame vsichko w lista
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select SerialNumber,NameBook,Author,ZANR from electronicbooks ";
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();

            while(rs.next()){
                String SearchBookName=rs.getString("NameBook");
                String SearchBookAuthor=rs.getString("Author");
                String SearchBookSerialNumber=rs.getString("SerialNumber");
                String SearchBookZanr=rs.getString("ZANR");

            SerachBarList.add(new ModleTableElectronicBooks(SearchBookName,SearchBookAuthor,SearchBookSerialNumber,SearchBookZanr));
            }

            col_NameElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookName"));
            col_AuthorElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookAuthor"));
            col_SerialNumberElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookSerialNumber"));
            col_ZanrElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookZanr"));

            TableElectronicBooks.setItems(SerachBarList);

            //create filtered list
            FilteredList<ModleTableElectronicBooks> filteredData=new FilteredList<>(SerachBarList, b->true);
            SearchBar.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(ModleTableElectronicBooks->{
                    if(newValue.isEmpty()||newValue.isBlank()||newValue==null){
                       return true;
                    }

                    String searchKeyWord=newValue.toLowerCase();

                    if (ModleTableElectronicBooks.getElectronicBookName().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (ModleTableElectronicBooks.getElectronicBookAuthor().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (ModleTableElectronicBooks.getElectronicBookSerialNumber().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (ModleTableElectronicBooks.getElectronicBookZanr().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else{
                        return false;
                    }

                });
            });

            SortedList<ModleTableElectronicBooks> sortedDate=new SortedList<>(filteredData);
sortedDate.comparatorProperty().bind(TableElectronicBooks.comparatorProperty());

TableElectronicBooks.setItems(sortedDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//to here



    //syzdavame buton read
    //sled natiskane vzimame imeto pridobito ot getSelectedElectronicBook metoda i sravnqvame imenata na knigite s tezi v switch case-a ako imeto syvpadne otvarq knigata inache dava gresha

    @FXML
    void ReadElectronicBooksOnAction(ActionEvent event) {
        //създажаме променливата в която ще запазваме pdf файла
        File file=null;
//pravim switch case chrez koito opredelqme koq tochno kniga da se otwori spored  imeto na knigata ot selektiraniq red (imeto na tazi kniga se zapazva v tazi promenliva-ElectronicBooksNameValue)
        switch (ElectronicBooksNameValue){
            case "Пипи Дългото Чорапче":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\PipiDylgotoChorapche.pdf");
                break;
            case "Снежанка и седемте джуджета":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\SnezankaISedemteDzudzeta.pdf");
                break;
            case "Красавицата и звяра":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\KrasavocataIZvqra.pdf");
                break;
            case "Червената Шапчица":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Червената Шапчица.pdf");
                break;
            case "Пепеляшка":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Пепеляшка.pdf");
                break;
            case "Вълкът и седемте козлета":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Вълкът и седемте козлета.pdf");
                break;
                //Пепеляшка
            case "АЗ СЪМ ВИНОВНА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Азсъмвиновна.pdf");
                break;
            case "ГРЪМОТЕВИЧНИЯТ ДОМ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Гръмотевичниятдом.pdf");
                break;
            case "АСТРАЛНА ВРЪЗКА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Астралнавръзка.pdf");
                break;
            case "26 ИСТОРИИ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\26истории.pdf");
                break;
            case "БУРЯТА НА ВЕКА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Бурятанавека.pdf");
                break;
            case "47 РОНИНИ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\47 ронини.pdf");
                break;
            case "ДЖУЛИЯ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Джулия.pdf");
                break;
            case "Агентът":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\агентът.pdf");
                break;
            case "Първа книга на Адам и Ева":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\ПърваКнигаНаАдамИЕва.pdf");
                break;
            case "ВХОДЪТ ЗА РАЯ ПЛАТЕН":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Входътзараяплатен.pdf");
                break;
            case "Адора":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Адора.pdf");
                break;
            case "ВСИЧКО Е СЪДБОВНО 14 МИСТЕРИИ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Всичкоесъдбовно14мистерии.pdf");
                break;
            case "АБОРТ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\аборт.pdf");
                break;
            case "АМЕРИКАНСКИ БОГОВЕ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\АмериканскиБогове.pdf");
                break;
            case "47-ИЯТ САМУРАЙ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\47иятсамурай.pdf");
                break;
            case "13-ТЕ СВЕТИНИ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\13-те светини.pdf");
                break;
            case "66 ГРАДУСА СЕВЕРНА ШИРИНА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\66градусасевернаширина.pdf");
                break;
            case "Ад на колела":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Аднаколела.pdf");
                break;
            case "АЗ И ДЪЩЕРЯ МИ":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\АзИДъщеряМи.pdf");
                break;
            case "АЛБИНА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Албина.pdf");
                break;
            case "БУИК 8":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Буик8.pdf");
                break;
            case "АЙРИС":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\Айрис.pdf");
                break;
            case "АЗ, ВЕЩИЦАТА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\аз,вещицата.pdf");
                break;
            case "ВЕЩИЦИТЕ: ПОЛУНОЩ КНИГА ПЪРВА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\ВещицитеПолунощКнигаПърва.pdf");
                break;
            case "АЛЕНА КРАЛИЦА":
                file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\АленаКралица.pdf");
                break;
        }

//ако има такъв файл го отваряме ако не дава грешка
        try {
           // File file = new File("D:\\LibraryInterface\\target\\classes\\com\\example\\libraryinterface\\PipiDylgotoChorapche.pdf");

            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Not exist file");
                }
            }else{
                System.out.println("Not exist file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//filtri buttons
//syzdavame method za filtyra kato promenqme samo imeto za da nqma povtarqne na kod
    public void FilterAuthorMethod(String authorName){
        PreparedStatement pss = null;
        ResultSet rss=null;

        try {
            listTableViewElectronicBooks.clear();
            //pravim connection to Database i vkarvame vsichko w lista
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from electronicbooks where Author=? ";
            pss = con.prepareStatement(sql);
            pss.setString(1,authorName);
            rss= pss.executeQuery();
            while(rss.next()){
                //vkarvame vsichko v lista
                listTableViewElectronicBooks.add(new ModleTableElectronicBooks(rss.getString("NameBook"),rss.getString("Author"),rss.getString("SerialNumber"),rss.getString("ZANR")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_NameElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookName"));
        col_AuthorElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookAuthor"));
        col_SerialNumberElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookSerialNumber"));
        col_ZanrElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookZanr"));

        TableElectronicBooks.setItems(listTableViewElectronicBooks);

    }



    public void FilterZAnrMethod(String zanr){
        PreparedStatement pss = null;
        ResultSet rss=null;

        try {
            listTableViewElectronicBooks.clear();
            //pravim connection to Database i vkarvame vsichko w lista
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Ivan0877449404");

            // ResultSet rs=con.createStatement().executeQuery("select * from addbook Where email=?");//moze da go zapisha i taka no pri tozi nachin shte mi izvede cqlata bazadanni a ne samo za edin chovek
            String sql = "Select * from electronicbooks where ZANR=? ";
            pss = con.prepareStatement(sql);
            pss.setString(1,zanr);
            rss= pss.executeQuery();
            while(rss.next()){
                //vkarvame vsichko v lista
                listTableViewElectronicBooks.add(new ModleTableElectronicBooks(rss.getString("NameBook"),rss.getString("Author"),rss.getString("SerialNumber"),rss.getString("ZANR")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_NameElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookName"));
        col_AuthorElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookAuthor"));
        col_SerialNumberElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookSerialNumber"));
        col_ZanrElectronicBook.setCellValueFactory(new PropertyValueFactory<>("ElectronicBookZanr"));

        TableElectronicBooks.setItems(listTableViewElectronicBooks);

    }

//premahvane na filtrite
    //премахваме филтрите като резтартираме цялото приложение, чрез извикване на главния метод
    @FXML
    void WithoutFiltersOnAction(ActionEvent event) {
        listTableViewElectronicBooks.clear();
        SerachBarList.clear();
        StartMethod();
    }



    @FXML
    void authorBratqGrimFiltyrOnAction(ActionEvent event)  {
        FilterAuthorMethod("Братя Грим");
    }

    @FXML
    void authorAstridLingrendFiltyrOnAction(ActionEvent event) {
        FilterAuthorMethod("Астрид Линдгрен");
    }

    @FXML
    void authorMadamDioBomonFiltyrOnAction(ActionEvent event) {
        FilterAuthorMethod("Мадам дьо Бомон");
    }

    @FXML
    void authorSharlPeroFiltyrOnAction(ActionEvent event) {
        FilterAuthorMethod("Шарл Перо");
    }



    @FXML
    void authorAnRaisOnAction(ActionEvent event) {FilterAuthorMethod("АН РАЙС");}

    @FXML
    void authorAniVivantiFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("АНИ ВИВАНТИ");}

    @FXML
    void authorBiliLecOnAction(ActionEvent event) {FilterAuthorMethod("БИЛИ ЛЕЦ");}

    @FXML
    void authorByrtrisSmolOnAction(ActionEvent event) {FilterAuthorMethod("БЪРТРИС СМОЛ");}

    @FXML
    void authorDaniylSilwaFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("Даниъл Силва");}

    @FXML
    void authorDeboraHarkensOnAction(ActionEvent event) {FilterAuthorMethod("ДЕБОРА ХАРКНЕС");}

    @FXML
    void authorDiinKuncFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("ДИЙН КУНЦ");}

    @FXML
    void authorDjonDWindvFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("ДЖОАН Д. ВИНДЖ");}

    @FXML
    void authorDjuliAnUokyrOnAction(ActionEvent event) {FilterAuthorMethod("ДЖУЛИ АН УОКЪР");}

    @FXML
    void authorEndruLourynsFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("ЕНДРЮ ЛОУРЪНС");}

    @FXML
    void authorKoliinFolkynOnAction(ActionEvent event) {FilterAuthorMethod("КОЛИЙН ФОЛКНЪР");}

    @FXML
    void authorLeiGriinuudOnAction(ActionEvent event) {FilterAuthorMethod("ЛЕЙ ГРИЙНУУД");}

    @FXML
    void authorMaikylKraitynOnAction(ActionEvent event) {FilterAuthorMethod("МАЙКЪЛ КРАЙТЪН");}

    @FXML
    void authorMaikylRidpadOnAction(ActionEvent event) {FilterAuthorMethod("МАЙКЪЛ РИДПАТ");}

    @FXML
    void authorMaikylSkotOnAction(ActionEvent event) {FilterAuthorMethod("МАЙКЪЛ СКОТ, КОЛЕТ ФРИЙДМАН");}

    @FXML
    void authorNiilGeitynOnAction(ActionEvent event) {FilterAuthorMethod("НИЙЛ ГЕЙМЪН");}

    @FXML
    void authorPityrStrobFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("ПИТЪР СТРОБ");}

    @FXML
    void authorStiwynHyntyrOnAction(ActionEvent event) {FilterAuthorMethod("СТИВЪН ХЪНТЪР");}

    @FXML
    void authorStiwynKingFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("СТИВЪН КИНГ");}

    @FXML
    void authorWalentinPopovFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("ВАЛЕНТИН ПОПОВ - ВОТАН");}

    @FXML
    void authorWiktoriqAivqrdOnAction(ActionEvent event) {FilterAuthorMethod("ВИКТОРИЯ АЙВЯРД");}

    @FXML
    void authorZulVernFiltyrOnAction(ActionEvent event) {FilterAuthorMethod("Жул Верн");}



    @FXML
    void zanrPrikazkaFiltyrOnAction(ActionEvent event) {FilterZAnrMethod("приказка");}

    @FXML
    void zanrRomanFiltyrOnAction(ActionEvent event) {FilterZAnrMethod("роман");}


    @FXML
    void zanrFentyziFiltyrOnAction(ActionEvent event) {FilterZAnrMethod("фентъзи");}

    @FXML
    void zanrHoryrFiltyrOnAction(ActionEvent event) {FilterZAnrMethod("хорър");}

    @FXML
    void zanrTriloyrFiltyrOnAction(ActionEvent event) {FilterZAnrMethod("трилър");}

        @FXML
    void initialize() {
            assert ReadElectronicBooksFXID != null : "fx:id=\"ReadElectronicBooksFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert SearchBar != null : "fx:id=\"SearchBar\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert TableElectronicBooks != null : "fx:id=\"TableElectronicBooks\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert WithoutFiltersFXID != null : "fx:id=\"WithoutFiltersFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorAnRaisFXID != null : "fx:id=\"authorAnRaisFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorAniVivantiFiltyrFXID != null : "fx:id=\"authorAniVivantiFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorAstridLingrendFiltyrFXID != null : "fx:id=\"authorAstridLingrendFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorBiliLecFXID != null : "fx:id=\"authorBiliLecFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorBratqGrimFiltyrFXID != null : "fx:id=\"authorBratqGrimFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorByrtrisSmolFXID != null : "fx:id=\"authorByrtrisSmolFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorDaniylSilwaFiltyrFXID != null : "fx:id=\"authorDaniylSilwaFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorDeboraHarkensFXID != null : "fx:id=\"authorDeboraHarkensFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorDiinKuncFiltyrFXID != null : "fx:id=\"authorDiinKuncFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorDjonDWindvFiltyrFXID != null : "fx:id=\"authorDjonDWindvFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorDjuliAnUokyrFXID != null : "fx:id=\"authorDjuliAnUokyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorEndruLourynsFiltyrFXID != null : "fx:id=\"authorEndruLourynsFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorKoliinFolkynFXID != null : "fx:id=\"authorKoliinFolkynFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorLeiGriinuudFXID != null : "fx:id=\"authorLeiGriinuudFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorMadamDioBomonFiltyrFXID != null : "fx:id=\"authorMadamDioBomonFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorMaikylKraitynFXID != null : "fx:id=\"authorMaikylKraitynFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorMaikylRidpadFXID != null : "fx:id=\"authorMaikylRidpadFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorMaikylSkotFXID != null : "fx:id=\"authorMaikylSkotFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorNiilGeitynFXID != null : "fx:id=\"authorNiilGeitynFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorPityrStrobFiltyrFXID != null : "fx:id=\"authorPityrStrobFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorSharlPeroFiltyrFXID != null : "fx:id=\"authorSharlPeroFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorStiwynHyntyrFXID != null : "fx:id=\"authorStiwynHyntyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorStiwynKingFiltyrFXID != null : "fx:id=\"authorStiwynKingFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorWalentinPopovFiltyrFXID != null : "fx:id=\"authorWalentinPopovFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorWiktoriqAivqrdFXID != null : "fx:id=\"authorWiktoriqAivqrdFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert authorZulVernFiltyrFXID != null : "fx:id=\"authorZulVernFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert col_AuthorElectronicBook != null : "fx:id=\"col_AuthorElectronicBook\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert col_NameElectronicBook != null : "fx:id=\"col_NameElectronicBook\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert col_SerialNumberElectronicBook != null : "fx:id=\"col_SerialNumberElectronicBook\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert col_ZanrElectronicBook != null : "fx:id=\"col_ZanrElectronicBook\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert returnButtonFXID != null : "fx:id=\"returnButtonFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert zanrFentyziFiltyrFXID != null : "fx:id=\"zanrFentyziFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert zanrHoryrFiltyrFXID != null : "fx:id=\"zanrHoryrFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert zanrPrikazkaFiltyrFXID != null : "fx:id=\"zanrPrikazkaFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert zanrRomanFiltyrFXID != null : "fx:id=\"zanrRomanFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
            assert zanrTriloyrFiltyrFXID != null : "fx:id=\"zanrTriloyrFiltyrFXID\" was not injected: check your FXML file 'ElectronicBooks.fxml'.";
    }


}
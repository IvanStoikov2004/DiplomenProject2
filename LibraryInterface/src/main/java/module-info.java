module com.example.libraryinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.libraryinterface to javafx.fxml;
    exports com.example.libraryinterface;
}
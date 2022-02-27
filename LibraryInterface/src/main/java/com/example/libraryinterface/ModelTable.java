package com.example.libraryinterface;
//towa e klasa chrez koito rabotim s informaciqta za da popylnim TableView(Nuzen ni e) i e po prigledno

import java.util.Date;

//Ima dva klasa za TableView// towa e ediniq(vtoriq e HelloController
public class ModelTable {

String BookName,Author,SerialNumber,Zanr,emailPeople,date;

    public ModelTable(String bookName, String author, String serialNumber, String zanr, String EmailPeople,String DateAddBook) {
        BookName = bookName;
        Author = author;
        SerialNumber = serialNumber;
        Zanr = zanr;
        emailPeople=EmailPeople;
        date=DateAddBook;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthor() {
        return Author;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public String getZanr() {
        return Zanr;
    }

    public String getEmail() {return emailPeople;}

    public String getDate() {return date;}
}

package com.example.libraryinterface;
//klas za upravlenie na table View
public class ModleTableElectronicBooks {

    String ElectronicBookName,ElectronicBookAuthor,ElectronicBookSerialNumber,ElectronicBookZanr;

    public ModleTableElectronicBooks(String electronicBookName, String electronicBookAuthor, String electronicBookSerialNumber, String electronicBookZanr) {
        ElectronicBookName = electronicBookName;
        ElectronicBookAuthor = electronicBookAuthor;
        ElectronicBookSerialNumber = electronicBookSerialNumber;
        ElectronicBookZanr = electronicBookZanr;
    }

    public String getElectronicBookName() {
        return ElectronicBookName;
    }

    public void setElectronicBookName(String electronicBookName) {
        ElectronicBookName = electronicBookName;
    }

    public String getElectronicBookAuthor() {
        return ElectronicBookAuthor;
    }

    public void setElectronicBookAuthor(String electronicBookAuthor) {
        ElectronicBookAuthor = electronicBookAuthor;
    }

    public String getElectronicBookSerialNumber() {
        return ElectronicBookSerialNumber;
    }

    public void setElectronicBookSerialNumber(String electronicBookSerialNumber) {
        ElectronicBookSerialNumber = electronicBookSerialNumber;
    }

    public String getElectronicBookZanr() {
        return ElectronicBookZanr;
    }

    public void setElectronicBookZanr(String electronicBookZanr) {
        ElectronicBookZanr = electronicBookZanr;
    }
}

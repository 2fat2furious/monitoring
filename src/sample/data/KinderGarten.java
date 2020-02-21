package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KinderGarten {
    private long idKindergarten;
    private StringProperty shortTitle = new SimpleStringProperty();
    private StringProperty fullTitle = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty fioManageress = new SimpleStringProperty();

    public KinderGarten(long idKindergarten, String shortTitle, String fullTitle, String address, String phone, String fioManageress) {
        this.idKindergarten = idKindergarten;
        this.shortTitle.set(shortTitle);
        this.fullTitle.set(fullTitle);
        this.address.set(address);
        this.phone.set(phone);
        this.fioManageress.set(fioManageress);
    }

    public long getIdKindergarten() {
        return idKindergarten;
    }

    public void setIdKindergarten(long idKindergarten) {
        this.idKindergarten = idKindergarten;
    }

    public String getShortTitle() {
        return shortTitle.get();
    }

    public StringProperty shortTitleProperty() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle.set(shortTitle);
    }

    public String getFullTitle() {
        return fullTitle.get();
    }

    public StringProperty fullTitleProperty() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle.set(fullTitle);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getFioManageress() {
        return fioManageress.get();
    }

    public StringProperty fioManageressProperty() {
        return fioManageress;
    }

    public void setFioManageress(String fioManageress) {
        this.fioManageress.set(fioManageress);
    }
}


package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KinderGarten {
    private long idKinderGarten;
    private StringProperty shortTitle = new SimpleStringProperty();
    private StringProperty fullTitle = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty fio = new SimpleStringProperty();


    public KinderGarten(long idKinderGarten, String shortTitle, String fullTitle, String address, String phone, String fio) {
        this.idKinderGarten = idKinderGarten;
        this.shortTitle.set(shortTitle);
        this.fullTitle.set(fullTitle);
        this.address.set(address);
        this.phone.set(phone);
        this.fio.set(fio);
    }

    public KinderGarten() {
    }

    public long getIdKinderGarten() {
        return idKinderGarten;
    }

    public void setIdKinderGarten(long idKinderGarten) {
        this.idKinderGarten = idKinderGarten;
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

    public String getFio() {
        return fio.get();
    }

    public StringProperty fioProperty() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }
}


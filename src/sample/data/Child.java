package sample.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Child {


    private long id_c;
    private StringProperty name = new SimpleStringProperty();
    private SimpleObjectProperty<Date> dateOfBirth = new SimpleObjectProperty<>();
    private StringProperty placeOfBirth = new SimpleStringProperty();
    private StringProperty homePhone = new SimpleStringProperty();
    private StringProperty homeAddress = new SimpleStringProperty();
    private StringProperty familyComposition = new SimpleStringProperty();
    private StringProperty healthStatus = new SimpleStringProperty();
    private boolean socialAssistance;
    private long idKinderGarten;
    private StringProperty titleGroup = new SimpleStringProperty();
    private long id_ay;

    public Child(long id_c, String name, Date dateOfBirth, String placeOfBirth,
                 String homePhone, String homeAddress, String familyComposition,
                 String healthStatus) {
        this.id_c = id_c;
        this.name.set(name);
        this.dateOfBirth.set(dateOfBirth);
        this.placeOfBirth.set(placeOfBirth);
        this.homePhone.set(homePhone);
        this.homeAddress.set(homeAddress);
        this.familyComposition.set(familyComposition);
        this.healthStatus.set(healthStatus);
    }

    public Child() {
    }

    public long getId_c() {
        return id_c;
    }

    public void setId_c(long id_c) {
        this.id_c = id_c;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Date getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleObjectProperty<Date> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getPlaceOfBirth() {
        return placeOfBirth.get();
    }

    public StringProperty placeOfBirthProperty() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth.set(placeOfBirth);
    }

    public String getHomePhone() {
        return homePhone.get();
    }

    public StringProperty homePhoneProperty() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone.set(homePhone);
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public StringProperty homeAddressProperty() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress.set(homeAddress);
    }

    public String getFamilyComposition() {
        return familyComposition.get();
    }

    public StringProperty familyCompositionProperty() {
        return familyComposition;
    }

    public void setFamilyComposition(String familyComposition) {
        this.familyComposition.set(familyComposition);
    }

    public String getHealthStatus() {
        return healthStatus.get();
    }

    public StringProperty healthStatusProperty() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus.set(healthStatus);
    }

    public boolean isSocialAssistance() {
        return socialAssistance;
    }

    public void setSocialAssistance(boolean socialAssistance) {
        this.socialAssistance = socialAssistance;
    }

    public long getIdKinderGarten() {
        return idKinderGarten;
    }

    public void setIdKinderGarten(long idKinderGarten) {
        this.idKinderGarten = idKinderGarten;
    }

    public String getTitleGroup() {
        return titleGroup.get();
    }

    public StringProperty titleGroupProperty() {
        return titleGroup;
    }

    public void setTitleGroup(String titleGroup) {
        this.titleGroup.set(titleGroup);
    }

    public long getId_ay() {
        return id_ay;
    }

    public void setId_ay(long id_ay) {
        this.id_ay = id_ay;
    }

    public StringProperty fioProperty() {
        return name;
    }
}

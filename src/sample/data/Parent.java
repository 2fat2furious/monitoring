package sample.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Timestamp;

public class Parent {

    private long id_p;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty education = new SimpleStringProperty();
    private StringProperty placeOfWork = new SimpleStringProperty();
    private StringProperty position = new SimpleStringProperty();
    private StringProperty workPhone = new SimpleStringProperty();
    private SimpleObjectProperty<Date> dateOfBirth = new SimpleObjectProperty<>();

    public Parent(long id_p, String name, String education, String placeOfWork, String position, String workPhone, Date dateOfBirth) {
        this.id_p = id_p;
        this.name.set(name);
        this.education.set(education);
        this.placeOfWork.set(placeOfWork);
        this.position.set(position);
        this.workPhone.set(workPhone);
        this.dateOfBirth.set(dateOfBirth);
    }

    public Parent() {
    }

    public long getId_p() {
        return id_p;
    }

    public void setId_p(long id_p) {
        this.id_p = id_p;
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

    public String getEducation() {
        return education.get();
    }

    public StringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String getPlaceOfWork() {
        return placeOfWork.get();
    }

    public StringProperty placeOfWorkProperty() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork.set(placeOfWork);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getWorkPhone() {
        return workPhone.get();
    }

    public StringProperty workPhoneProperty() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone.set(workPhone);
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

    public StringProperty fioProperty() {
        return name;
    }
}
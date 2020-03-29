package sample.data;

import javafx.beans.property.*;

public class Educator {

    private long idKinderGarten;
    private StringProperty fio = new SimpleStringProperty();
    private StringProperty education = new SimpleStringProperty();
    private StringProperty position = new SimpleStringProperty();
    private IntegerProperty workExperience = new SimpleIntegerProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty nameKinderGarten = new SimpleStringProperty();

    public Educator(long idKinderGarten, String fio, String education, String position, int workExperience, String phone) {
        this.idKinderGarten = idKinderGarten;
        this.fio.set(fio);
        this.education.set(education);
        this.position.set(position);
        this.workExperience.set(workExperience);
        this.phone.set(phone);
    }

    public Educator() {
    }

    public String getNameKinderGarten() {
        return nameKinderGarten.get();
    }

    public StringProperty nameKinderGartenProperty() {
        return nameKinderGarten;
    }

    public void setNameKinderGarten(String nameKinderGarten) {
        this.nameKinderGarten.set(nameKinderGarten);
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

    public long getIdKinderGarten() {
        return idKinderGarten;
    }

    public void setIdKinderGarten(long idKinderGarten) {
        this.idKinderGarten = idKinderGarten;
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

    public String getEducation() {
        return education.get();
    }

    public StringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public int getWorkExperience() {
        return workExperience.get();
    }

    public IntegerProperty workExperienceProperty() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience.set(workExperience);
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
}

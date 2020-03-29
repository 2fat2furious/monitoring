package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChildSection {

    private long id_c;
    private StringProperty institutionName = new SimpleStringProperty();
    private StringProperty sectionName = new SimpleStringProperty();

    public ChildSection(long id_c, String institutionName, String sectionName){
        this.id_c = id_c;
        this.institutionName.set(institutionName);
        this.sectionName.set(sectionName);
    }

    public long getId_c() {
        return id_c;
    }

    public void setId_c(long id_c) {
        this.id_c = id_c;
    }

    public String getInstitutionName() {
        return institutionName.get();
    }

    public StringProperty institutionNameProperty() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName.set(institutionName);
    }

    public String getSectionName() {
        return sectionName.get();
    }

    public StringProperty sectionNameProperty() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName.set(sectionName);
    }
}

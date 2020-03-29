package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Section {

    private StringProperty institutionName = new SimpleStringProperty();
    private StringProperty sectionName = new SimpleStringProperty();

    public Section(String institutionName, String sectionName){
        this.institutionName.set(institutionName);
        this.sectionName.set(sectionName);
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

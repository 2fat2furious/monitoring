package sample.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GroupType {

    private long code;
    private StringProperty titleGroupType = new SimpleStringProperty();
    private IntegerProperty ageInitial = new SimpleIntegerProperty();
    private IntegerProperty ageEnd = new SimpleIntegerProperty();

    public GroupType(long code, String titleGroupType, int ageInitial, int ageEnd){
        this.code = code;
        this.titleGroupType.set(titleGroupType);
        this.ageInitial.set(ageInitial);
        this.ageEnd.set(ageEnd);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getTitleGroupType() {
        return titleGroupType.get();
    }

    public StringProperty titleGroupTypeProperty() {
        return titleGroupType;
    }

    public void setTitleGroupType(String titleGroupType) {
        this.titleGroupType.set(titleGroupType);
    }

    public int getAgeInitial() {
        return ageInitial.get();
    }

    public IntegerProperty ageInitialProperty() {
        return ageInitial;
    }

    public void setAgeInitial(int ageInitial) {
        this.ageInitial.set(ageInitial);
    }

    public int getAgeEnd() {
        return ageEnd.get();
    }

    public IntegerProperty ageEndProperty() {
        return ageEnd;
    }

    public void setAgeEnd(int ageEnd) {
        this.ageEnd.set(ageEnd);
    }
}

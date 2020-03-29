package sample.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AcademicYear {

    private long id_ay;
    private IntegerProperty yearInitial = new SimpleIntegerProperty();
    private IntegerProperty yearEnd = new SimpleIntegerProperty();

    public AcademicYear(long id_ay, int yearInitial, int yearEnd){
        this.id_ay = id_ay;
        this.yearInitial.set(yearInitial);
        this.yearEnd.set(yearEnd);
    }

    public long getId_ay() {
        return id_ay;
    }

    public void setId_ay(long id_ay) {
        this.id_ay = id_ay;
    }

    public int getYearInitial() {
        return yearInitial.get();
    }

    public IntegerProperty yearInitialProperty() {
        return yearInitial;
    }

    public void setYearInitial(int yearInitial) {
        this.yearInitial.set(yearInitial);
    }

    public int getYearEnd() {
        return yearEnd.get();
    }

    public IntegerProperty yearEndProperty() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd.set(yearEnd);
    }
}

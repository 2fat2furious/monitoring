package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeResult {

    private long id_tr;
    private StringProperty nameMark = new SimpleStringProperty();
    private StringProperty descMark = new SimpleStringProperty();

    public TypeResult(long id_tr, String nameMark, String descMark) {
        this.id_tr = id_tr;
        this.nameMark.set(nameMark);
        this.descMark.set(descMark);
    }

    public TypeResult(){}

    public long getId_tr() {
        return id_tr;
    }

    public void setId_tr(long id_tr) {
        this.id_tr = id_tr;
    }

    public String getNameMark() {
        return nameMark.get();
    }

    public StringProperty nameMarkProperty() {
        return nameMark;
    }

    public void setNameMark(String nameMark) {
        this.nameMark.set(nameMark);
    }

    public String getDescMark() {
        return descMark.get();
    }

    public StringProperty descMarkProperty() {
        return descMark;
    }

    public void setDescMark(String descMark) {
        this.descMark.set(descMark);
    }
}

package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeOfDevelopmentProgram {

    private long code_p;
    private StringProperty name_p = new SimpleStringProperty();

    public TypeOfDevelopmentProgram(long code_p, String name_p) {
        this.code_p = code_p;
        this.name_p.set(name_p);
    }

    public TypeOfDevelopmentProgram(){}


    public long getCode_p() {
        return code_p;
    }

    public void setCode_p(long code_p) {
        this.code_p = code_p;
    }

    public String getName_p() {
        return name_p.get();
    }

    public StringProperty name_pProperty() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p.set(name_p);
    }
}

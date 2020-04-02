package sample.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Group {
    private long kinderGartenId;
    private long academicYearId;
    private StringProperty title = new SimpleStringProperty();

    public Group(long kinderGartenId, long academicYearId, String title) {
        this.kinderGartenId = kinderGartenId;
        this.academicYearId = academicYearId;
        this.title.setValue(title);
    }

    public long getKinderGartenId() {
        return kinderGartenId;
    }

    public void setKinderGartenId(long kinderGartenId) {
        this.kinderGartenId = kinderGartenId;
    }

    public long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (kinderGartenId != group.kinderGartenId) return false;
        if (academicYearId != group.academicYearId) return false;
        return Objects.equals(title.get(), group.title.get());
    }

    @Override
    public int hashCode() {
        int result = (int) (kinderGartenId ^ (kinderGartenId >>> 32));
        result = 31 * result + (int) (academicYearId ^ (academicYearId >>> 32));
        result = 31 * result + (title != null ? title.get().hashCode() : 0);
        return result;
    }
}

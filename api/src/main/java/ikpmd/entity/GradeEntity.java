package ikpmd.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sidney on 1/29/2017.
 */
@Entity(name="GradeEntity")
@Table(name="grade")
public class GradeEntity implements EntityObject {

    @EmbeddedId
    private GradeCompoundKey gradeCompoundKey;

    @Column(name="grade")
    private double grade;

    @Column(name="period")
    private int period;

    @Column(name="year")
    private int year;

    public GradeEntity() {}
    public GradeEntity(String studentNr, String courseCode) {
        this.gradeCompoundKey = new GradeCompoundKey(studentNr, courseCode);
    }

    public GradeCompoundKey getGradeCompoundKey() {
        return gradeCompoundKey;
    }
    public void setGradeCompoundKey(GradeCompoundKey gradeCompoundKey) {
        this.gradeCompoundKey = gradeCompoundKey;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getPeriod() {
        return period;
    }
    public void setPeriod(int period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}

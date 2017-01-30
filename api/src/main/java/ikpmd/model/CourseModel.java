package ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/19/2017.
 */
public class CourseModel implements Serializable {

    private String code;
    private String name;
    private double grade;
    private int ec;
    private int period;
    private int year;

    public CourseModel() {}
    public CourseModel(String code, String name, double grade, int ec, int period, int year) {
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.ec = ec;
        this.period = period;
        this.year = year;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getEc() {
        return ec;
    }
    public void setEc(int ec) {
        this.ec = ec;
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

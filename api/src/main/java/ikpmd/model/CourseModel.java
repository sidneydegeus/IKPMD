package ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/19/2017.
 */
public class CourseModel implements Serializable {

    private String code;
    private int year;
    private String name;

    public CourseModel() {}
    public CourseModel(String code, int year, String name) {} {
        this.code = code;
        this.year = year;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
